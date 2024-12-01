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
		rw.Unlock()

		messagesCopy := make([]any, 0)
		for message := range messages {
			messagesCopy = append(messagesCopy, message)
		}

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
		var m map[string]any
		if err := json.Unmarshal(msg.Body, &m); err != nil {
			return err
		}

		topology, ok := m["topology"].(map[string]any)
		if !ok {
			return fmt.Errorf("invalid topology format")
		}

		newNodeIDs := []string{}
		for id, values := range topology {
			valList, ok := values.([]any)
			if !ok {
				return fmt.Errorf("invalid value list for node %s", id)
			}
			if node.ID() == id {
				for _, value := range valList {
					v, ok := value.(string)
					if !ok {
						return fmt.Errorf("invalid node ID in topology")
					}
					newNodeIDs = append(newNodeIDs, v)
				}
			} else {
				toSend := map[string]any{"type": "topology", "topology": map[string]any{id: valList}}
				node.Send(id, toSend)
			}
		}
		rw.Lock()
		node.Init(node.ID(), newNodeIDs)
		rw.Unlock()

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
