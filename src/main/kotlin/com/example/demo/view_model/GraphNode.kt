package com.example.demo.view_model

import com.example.demo.app.Styles
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

abstract class GraphNode: Circle() {
    val connections = mutableListOf<Connection>()

    val xCoordinateProperty = SimpleDoubleProperty(0.0)
    val yCoordinateProperty = SimpleDoubleProperty(0.0)
}