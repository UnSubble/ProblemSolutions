package quest1

import (
	"encoding/json"
	"log"

	maelstrom "github.com/jepsen-io/maelstrom/demo/go"
)

func main_1() {
	node := maelstrom.NewNode()
	node.Handle("generate", func(msg maelstrom.Message) error {
		var m map[string]any
		err := json.Unmarshal(msg.Body, &m)

		if err != nil {
			return err
		}

		m["type"] = "echo_ok"

		return node.Reply(msg, m)
	})

	err := node.Run()

	if err != nil {
		log.Fatal("an error occured!")
	}

}
