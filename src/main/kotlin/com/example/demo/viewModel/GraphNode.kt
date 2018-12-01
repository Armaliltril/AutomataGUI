package com.example.demo.viewModel

import javafx.beans.property.SimpleStringProperty
import javafx.scene.shape.Circle
import com.example.demo.app.Styles
import tornadofx.*

open class GraphNode: Circle() {

    val nameProperty = SimpleStringProperty("DEFAULT")
    val connections = mutableListOf<GraphConnection>()

    init {
        radius = 25.0
        addClass(Styles.graphNode)
    }
}