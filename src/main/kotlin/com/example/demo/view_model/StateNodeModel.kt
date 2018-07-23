package com.example.demo.view_model

import tornadofx.*

class StateNodeModel(var node: StateNode): ViewModel() {
    var name = bind { node.stateNameProperty }
    var type = bind { node.stateTypeProperty }
    var xCoordinate = bind { node.xCoordinateProperty }
    var yCoordinate = bind { node.yCoordinateProperty }
}
