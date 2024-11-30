package quest2

import (
	"encoding/json"
	"fmt"
	"log"

	maelstrom "github.com/jepsen-io/maelstrom/demo/go"
)

func main_2() {
	node := maelstrom.NewNode()
	c := make(chan int32, 1)
	c <- 1
	node.Handle("generate", func(msg maelstrom.Message) error {
		var m map[string]any
		err := json.Unmarshal(msg.Body, &m)

		if err != nil {
			return err
		}

		m["type"] = "generate_ok"
		val := <-c
		m["id"] = fmt.Sprintf("%s%d", node.ID(), val)
		c <- val + 1

		return node.Reply(msg, m)
	})

	err := node.Run()

	if err != nil {
		log.Fatal("an error occured!")
	}

}
