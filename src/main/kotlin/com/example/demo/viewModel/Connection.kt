package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import tornadofx.*

class Connection(var startNode: GraphNode, var endNode: GraphNode): GraphConnection() {

    enum class MessageType {
        PRIVATE,
        SHARED,
        SENDING,
        RECEIVING
    }

    override val endNodeProperty = SimpleObjectProperty(StateNode())
    override val startNodeProperty = SimpleObjectProperty(StateNode())

    val typeProperty = SimpleObjectProperty(MessageType.PRIVATE)
}