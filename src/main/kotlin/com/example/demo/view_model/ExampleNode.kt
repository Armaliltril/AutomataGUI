package com.example.demo.view_model

import com.example.demo.automata.AutomataState
import javafx.scene.paint.Color
import javafx.scene.shape.Circle

class ExampleNode: Node() {
    val automataState = AutomataState("default", AutomataState.QueueType.GENERAL)

}