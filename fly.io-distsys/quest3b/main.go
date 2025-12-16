package main

import (
	"encoding/json"
	"fmt"
	"log"
	"sync"

	maelstrom "github.com/jepsen-io/maelstrom/demo/go"
)

func main() {
	node := maelstrom.NewNode()
	messages := make(map[any]bool)
	var rw sync.RWMutex

	node.Handle("broadcast", func(msg maelstrom.Message) error {
		var m map[string]any
		if err := json.Unmarshal(msg.Body, &m); err != nil {
			return err
		}

		message := m["message"]

		rw.Lock()
		messages[message] = true

		messagesCopy := make([]any, 0)
		for message := range messages {
			messagesCopy = append(messagesCopy, message)
		}
		rw.Unlock()

		rom := map[string]any{"type": "register", "messages": messagesCopy}

		for _, tID := range node.NodeIDs() {
			if tID == node.ID() {
				continue
			}
			if err := node.Send(tID, rom); err != nil {
				log.Printf("Failed to send message to %s: %v", tID, err)
			}
		}

		return node.Reply(msg, map[string]any{"type": "broadcast_ok"})
	})

	node.Handle("read", func(msg maelstrom.Message) error {
		var m map[string]any
		if err := json.Unmarshal(msg.Body, &m); err != nil {
			return err
		}

		rw.RLock()
		messagesCopy := []any{}
		for message := range messages {
			messagesCopy = append(messagesCopy, message)
		}
		rw.RUnlock()

		m["type"] = "read_ok"
		m["messages"] = messagesCopy
		return node.Reply(msg, m)
	})

	node.Handle("topology", func(msg maelstrom.Message) error {
		return node.Reply(msg, map[string]any{"type": "topology_ok"})
	})

	node.Handle("topology_ok", func(msg maelstrom.Message) error {
		log.Printf("Received topology_ok from %s", msg.Src)
		return nil
	})

	node.Handle("register", func(msg maelstrom.Message) error {
		var m map[string]any
		if err := json.Unmarshal(msg.Body, &m); err != nil {
			return err
		}

		messageArr, ok := m["messages"].([]any)
		if !ok {
			return fmt.Errorf("missing 'messages' field")
		}

		rw.Lock()
		for _, message := range messageArr {
			messages[message] = true
		}
		rw.Unlock()
		return nil
	})

	if err := node.Run(); err != nil {
		log.Fatalf("Node failed to run: %v", err)
	}
}
