package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import tornadofx.*

class StateNode: GraphNode() {

    enum class QueueType {
        INITIAL,
        GENERAL,
        FINAL;
    }

    val typeProperty = SimpleObjectProperty<QueueType> (QueueType.GENERAL)

    init {
        applyStyleByType()
    }

    fun applyStyleByType() {
        when(typeProperty.value) {
            QueueType.INITIAL -> style { fill = Color.GREEN }
            QueueType.GENERAL -> style { fill = Color.RED }
            QueueType.FINAL -> style { fill = Color.BLUE }
            else -> throw RuntimeException("Unexpected Queue Type")
        }
    }
}