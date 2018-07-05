package com.example.demo.automata

data class Connection(var name: String,
                      var from: Automata,
                      var to: Automata,
                      var condition: String,
                      var type: MessageType) {

    enum class MessageType {
        SENDING,
        RECEIVING
    }
}
