package main

import (
	"encoding/json"
	"log"
	"sync"

	maelstrom "github.com/jepsen-io/maelstrom/demo/go"
)

func main() {
	node := maelstrom.NewNode()
	messages := make(map[string][]any)
	edges := make(map[string][]string)
	var c sync.Mutex

	node.Handle("broadcast", func(msg maelstrom.Message) error {
		var m map[string]any
		err := json.Unmarshal(msg.Body, &m)
		if err != nil {
			return err
		}
		m["type"] = "broadcast_ok"
		c.Lock()
		if messages[node.ID()] == nil {
			messages[node.ID()] = []any{}
		}
		messages[node.ID()] = append(messages[node.ID()], m["message"])
		c.Unlock()
		delete(m, "message")
		return node.Reply(msg, m)
	})

	node.Handle("read", func(msg maelstrom.Message) error {
		var m map[string]any
		err := json.Unmarshal(msg.Body, &m)
		if err != nil {
			return err
		}
		m["type"] = "read_ok"
		m["messages"] = []any{}
		c.Lock()
		for _, nodeID := range edges[node.ID()] {
			m["messages"] = append(m["messages"].([]any), messages[nodeID]...)
		}
		m["messages"] = append(m["messages"].([]any), messages[node.ID()]...)
		c.Unlock()
		return node.Reply(msg, m)
	})

	node.Handle("topology", func(msg maelstrom.Message) error {
		var m map[string]any
		err := json.Unmarshal(msg.Body, &m)
		if err != nil {
			return err
		}
		m["type"] = "topology_ok"
		delete(m, "topology")
		return node.Reply(msg, m)
	})

	err := node.Run()

	if err != nil {
		log.Fatal("an error occured!")
	}
}
