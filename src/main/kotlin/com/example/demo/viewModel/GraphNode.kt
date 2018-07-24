package com.example.demo.viewModel

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.shape.Circle

abstract class GraphNode: Circle() {
    val connections = mutableListOf<GraphConnection>()

    val xCoordinateProperty = SimpleDoubleProperty(0.0)
    val yCoordinateProperty = SimpleDoubleProperty(0.0)
}