package com.example.demo.view

import com.example.demo.viewModel.StateNode
import com.example.demo.viewModel.StateNodeModel
import com.example.demo.viewModel.StateNode.QueueType
import tornadofx.*

class AutomataStateEditor: Fragment() {

    var stateModel = StateNodeModel(StateNode())

    override val root = vbox {
        form {
            fieldset {
                field("Name") {
                    textfield(stateModel.name) {
                        required()
                    }
                }
                field("Type") {
                    val queueTypes = QueueType.values().toList().observable()
                    combobox(stateModel.type, queueTypes)
                }
                field("X Coordinate") {
                    textfield(stateModel.xCoordinate) {
                        action {
                            relocate(stateModel.node)
                        }
                        required()
                    }
                }
                field("Y Coordinate") {
                    textfield(stateModel.yCoordinate) {
                        action {
                            relocate(stateModel.node)
                        }
                        required()
                    }
                }
                button("Save") {
                    action {
                        stateModel.commit()
                        stateModel.node.applyStyleByType()
                        relocate(stateModel.node)
                    }
                }
            }
        }
    }

    private fun relocate(node: StateNode) {
        node.relocate(node.xCoordinateProperty.value, node.yCoordinateProperty.value)
    }
}
