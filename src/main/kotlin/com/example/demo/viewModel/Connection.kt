package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

class Connection(startNode: StateNode = StateNode(), endNode: StateNode = StateNode()): GraphConnection(startNode, endNode) {

    enum class MessageType {
        PRIVATE,
        SHARED,
        SENDING,
        RECEIVING
    }

    override val startNodeProperty = SimpleObjectProperty(startNode)
    override val endNodeProperty = SimpleObjectProperty(endNode)

    val typeProperty = SimpleObjectProperty(MessageType.PRIVATE)
}