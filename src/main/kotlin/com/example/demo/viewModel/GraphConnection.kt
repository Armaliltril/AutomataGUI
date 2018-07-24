package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.shape.Line

abstract class GraphConnection: Line(){

    abstract val startNodeProperty: SimpleObjectProperty<out GraphNode>
    abstract val endNodeProperty: SimpleObjectProperty<out GraphNode>
}