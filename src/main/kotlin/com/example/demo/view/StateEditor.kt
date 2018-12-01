package com.example.demo.view

import com.example.demo.viewModel.GraphConnection
import com.example.demo.viewModel.GraphNode
import tornadofx.*

abstract class StateEditor: Fragment() {

    abstract val state: GraphNode
    abstract val connection: GraphConnection


    override val root = vbox {

    }
}