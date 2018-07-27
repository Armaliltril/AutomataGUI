package com.example.demo.view

import com.example.demo.viewModel.Connection
import com.example.demo.viewModel.ConnectionModel
import com.example.demo.viewModel.StateNode
import com.example.demo.viewModel.StateNodeModel
import com.example.demo.viewModel.StateNode.QueueType
import com.example.demo.viewModel.Connection.MessageType
import tornadofx.*

class AutomataStateEditor: Fragment() {

    var stateModel = StateNodeModel(StateNode())
    var connectionModel = ConnectionModel(Connection())

    override val root = vbox {
        form {
            fieldset("State") {
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
            }
            fieldset("Connection") {
                field("Name") {
                    textfield(connectionModel.name) {
                        required()
                    }
                }
                field("Type") {
                    val messageTypes = MessageType.values().toList().observable()
                    combobox(connectionModel.type, messageTypes)
                }
                //TODO: get automata's names list for combobox
                field("Start Node") {
                    textfield(connectionModel.startNode.name)
                }
                field("End Node") {
                    textfield(connectionModel.endNode.name)
                }
            }
            button("Save") {
                action {
                    saveChanges()
                }
            }
        }

        shortcut("Ctrl+S") {
            saveChanges()
        }
    }

    private fun relocate(node: StateNode) {
        node.relocate(node.xCoordinateProperty.value, node.yCoordinateProperty.value)
    }
    private fun saveChanges() {
        saveState()
        saveConnection()
    }
    private fun saveState() {
        stateModel.commit()
        stateModel.node.applyStyleByType()
        relocate(stateModel.node)
    }
    private fun saveConnection() {
        connectionModel.commit()
    }
}
