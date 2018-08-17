package com.example.demo.viewModel

import tornadofx.*

class StateNodeModel(var node: StateNode): ViewModel() {
    var name = bind { node.nameProperty }
    var type = bind { node.typeProperty }
    var x = bind { node.centerXProperty() }
    var y = bind { node.centerYProperty() }
}
