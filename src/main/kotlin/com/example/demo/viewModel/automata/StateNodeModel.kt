package com.example.demo.viewModel.automata

import com.example.demo.viewModel.GraphNodeModel

class StateNodeModel(node: StateNode): GraphNodeModel(node) {
    var type = bind { node.typeProperty }
}
