package com.example.demo.view.automata

import com.example.demo.view.StateEditor
import com.example.demo.viewModel.automata.ConnectionModel
import com.example.demo.viewModel.automata.StateNode
import com.example.demo.viewModel.automata.StateNodeModel
import com.example.demo.viewModel.automata.StateNode.QueueType
import com.example.demo.viewModel.automata.Connection.MessageType
import tornadofx.*

class AutomataStateEditor: StateEditor() {

    override val root = vbox {
        nodeModel as StateNodeModel
        connectionModel as ConnectionModel
        form {
            fieldset("State") {
                field("Name") {
                    textfield(nodeModel.name) {
                        required()
                    }
                }
                field("Type") {
                    val queueTypes = QueueType.values().toList().observable()
                    combobox(nodeModel.type, queueTypes)
                }
                field("X Coordinate") {
                    textfield(nodeModel.x) {
                        required()
                    }
                }
                field("Y Coordinate") {
                    textfield(nodeModel.y) {
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

    override fun saveNode() {
        super.saveNode()
        (nodeModel.node as StateNode).applyStyleByType()
    }
    override fun saveConnection() {
        super.saveConnection()
    }
}
