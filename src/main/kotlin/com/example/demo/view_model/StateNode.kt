package com.example.demo.view_model

import com.example.demo.app.Styles
import com.example.demo.automata.AutomataState
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.paint.Color
import tornadofx.*

class StateNode: GraphNode() {

    val stateNameProperty = SimpleStringProperty("DEFAULT")
    val stateTypeProperty = SimpleObjectProperty<AutomataState.QueueType> (AutomataState.QueueType.GENERAL)

    init {
        applyStyleByType()
    }

    fun applyStyleByType() {
        when(stateTypeProperty.value) {
            AutomataState.QueueType.INITIAL -> style { fill = Color.GREEN }
            AutomataState.QueueType.GENERAL -> style { fill = Color.RED }
            AutomataState.QueueType.FINAL -> style { fill = Color.BLUE }
            else -> throw RuntimeException("Unexpected Queue Type")
        }
    }
}