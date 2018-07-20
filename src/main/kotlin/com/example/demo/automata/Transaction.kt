package com.example.demo.automata

data class Transaction(var name: String,
                       var from: AutomataState,
                       var to: AutomataState,
                       var condition: String,
                       var type: MessageType) {

    enum class MessageType {
        PRIVATE,
        SHARED,
        SENDING,
        RECEIVING
    }
}
