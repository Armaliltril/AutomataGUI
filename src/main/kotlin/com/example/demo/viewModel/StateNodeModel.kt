package com.example.demo.viewModel

import tornadofx.*

class StateNodeModel(var node: StateNode): ViewModel() {
    var name = bind { node.stateNameProperty }
    var type = bind { node.stateTypeProperty }
    var xCoordinate = bind { node.xCoordinateProperty }
    var yCoordinate = bind { node.yCoordinateProperty }
}
