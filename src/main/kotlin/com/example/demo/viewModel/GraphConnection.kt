package com.example.demo.viewModel

import javafx.beans.property.SimpleObjectProperty
import javafx.scene.shape.Line

open class GraphConnection(startNode: GraphNode = GraphNode(), endNode: GraphNode = GraphNode()): Line(){

    open val startNodeProperty: SimpleObjectProperty<out GraphNode> = SimpleObjectProperty(startNode)
    open val endNodeProperty: SimpleObjectProperty<out GraphNode> = SimpleObjectProperty(endNode)
}