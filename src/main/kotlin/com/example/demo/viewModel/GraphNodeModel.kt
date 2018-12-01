package com.example.demo.viewModel

import tornadofx.*

open class GraphNodeModel(var node: GraphNode): ViewModel() {
    var name = bind { node.nameProperty }
    var x = bind { node.centerXProperty() }
    var y = bind { node.centerYProperty() }
}