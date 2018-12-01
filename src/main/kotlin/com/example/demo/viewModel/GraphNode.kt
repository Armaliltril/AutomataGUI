package com.example.demo.viewModel

import javafx.beans.property.SimpleStringProperty
import javafx.scene.shape.Circle

open class GraphNode: Circle() {

    val nameProperty = SimpleStringProperty("DEFAULT")
    val connections = mutableListOf<GraphConnection>()

    init {
        radius = 25.0
    }
}