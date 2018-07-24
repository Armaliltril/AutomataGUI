package com.example.demo.viewModel

import tornadofx.*

class StateNodeModel(var node: StateNode): ViewModel() {
    var name = bind { node.nameProperty }
    var type = bind { node.typeProperty }
    var xCoordinate = bind { node.xCoordinateProperty }
    var yCoordinate = bind { node.yCoordinateProperty }
}
