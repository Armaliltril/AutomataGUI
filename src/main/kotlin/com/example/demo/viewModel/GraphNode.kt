package com.example.demo.viewModel

import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.shape.Circle

abstract class GraphNode: Circle() {
    val connections = mutableListOf<GraphConnection>()

    init {
        radius = 25.0
    }
}