package com.example.demo.view

import com.example.demo.viewModel.GraphConnection
import com.example.demo.viewModel.GraphConnectionModel
import com.example.demo.viewModel.GraphNode
import com.example.demo.viewModel.GraphNodeModel
import tornadofx.*

open class StateEditor: Fragment() {

    val state = GraphNodeModel(GraphNode())
    val connection = GraphConnectionModel(GraphConnection())

    override val root = vbox {

    }
}