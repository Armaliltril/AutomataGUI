package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty

class Connection: GraphConnection() {

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