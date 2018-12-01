package com.example.demo.viewModel.automata

import com.example.demo.viewModel.GraphConnection
import javafx.beans.property.SimpleObjectProperty

class Connection(startNode: StateNode = StateNode(), endNode: StateNode = StateNode()): GraphConnection(startNode, endNode) {

    enum class MessageType {
        PRIVATE,
        SHARED,
        SENDING,
        RECEIVING
    }

    val typeProperty = SimpleObjectProperty(MessageType.PRIVATE)
}