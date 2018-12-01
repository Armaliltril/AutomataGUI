package com.example.demo.view

import com.example.demo.viewModel.automata.Connection
import com.example.demo.viewModel.automata.ConnectionModel
import com.example.demo.viewModel.automata.StateNode
import com.example.demo.viewModel.automata.StateNodeModel
import com.example.demo.viewModel.automata.StateNode.QueueType
import com.example.demo.viewModel.automata.Connection.MessageType
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
                    textfield(stateModel.x) {
                        required()
                    }
                }
                field("Y Coordinate") {
                    textfield(stateModel.y) {
                        required()
                    }
                }
            }
            fieldset("Connection") {
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

    private fun saveChanges() {
        saveState()
        saveConnection()
    }
    private fun saveState() {
        stateModel.commit()
        (stateModel.node as StateNode).applyStyleByType()
    }
    private fun saveConnection() {
        connectionModel.commit()
    }
}
