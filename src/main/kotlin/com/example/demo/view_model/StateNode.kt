package com.example.demo.view_model

import com.example.demo.automata.AutomataState

class StateNode: GraphNode() {
    val automataState = AutomataState("default", AutomataState.QueueType.GENERAL)
}