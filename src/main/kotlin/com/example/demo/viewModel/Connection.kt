package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty

class Connection: GraphConnection() {

    enum class MessageType {
        PRIVATE,
        SHARED,
        SENDING,
        RECEIVING
    }

    override val endNodeProperty = SimpleObjectProperty(StateNode())
    override val startNodeProperty = SimpleObjectProperty(StateNode())

    val nameProperty = SimpleStringProperty("DEFAULT")
    val typeProperty = SimpleObjectProperty(MessageType.PRIVATE)
}