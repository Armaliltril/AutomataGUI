package com.example.demo.view_model

import com.example.demo.app.Styles
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

abstract class GraphNode: Circle() {
    val connections = mutableListOf<Connection>()
    var xCoordinate = 0.0
    var yCoordinate = 0.0

    init {
        addClass(Styles.automataState)
    }
}