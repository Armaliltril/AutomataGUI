package com.example.demo.viewModel.automata

import com.example.demo.app.Styles
import com.example.demo.viewModel.GraphNode
import javafx.beans.property.SimpleObjectProperty
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
        addClass(Styles.automataState)
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