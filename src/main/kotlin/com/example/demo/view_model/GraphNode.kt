package com.example.demo.view_model

import com.example.demo.app.Styles
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

abstract class GraphNode: Circle() {
    val connections = mutableListOf<Connection>()

    init {
        addClass(Styles.automataState)
    }
}