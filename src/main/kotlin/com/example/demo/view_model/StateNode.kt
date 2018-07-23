package com.example.demo.view_model

import com.example.demo.app.Styles
import com.example.demo.automata.AutomataState
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class StateNode: GraphNode() {
    val automataState = AutomataState("default", AutomataState.QueueType.GENERAL)

    val stateNameProperty = SimpleStringProperty("default")
    val stateTypeProperty = SimpleObjectProperty<AutomataState.QueueType> (AutomataState.QueueType.GENERAL)

    init {
        addClass(Styles.automataState)
    }

    fun degubPrint() {
        println("( " + stateNameProperty.value + ", " + stateTypeProperty.value + "," + xCoordinateProperty.value + ":" + yCoordinateProperty.value  + ")")
    }
}