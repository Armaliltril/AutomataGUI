package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.shape.Line

open class GraphConnection(startNode: GraphNode = GraphNode(), endNode: GraphNode = GraphNode()): Line(){

    open val startNodeProperty: SimpleObjectProperty<GraphNode> = SimpleObjectProperty(startNode)
    open val endNodeProperty: SimpleObjectProperty<GraphNode> = SimpleObjectProperty(endNode)

    init {
        strokeWidth = 3.0
    }
}