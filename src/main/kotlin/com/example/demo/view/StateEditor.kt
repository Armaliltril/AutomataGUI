package com.example.demo.view

import com.example.demo.viewModel.GraphConnection
import com.example.demo.viewModel.GraphConnectionModel
import com.example.demo.viewModel.GraphNode
import com.example.demo.viewModel.GraphNodeModel
import tornadofx.*

open class StateEditor: Fragment() {

    val nodeModel = GraphNodeModel(GraphNode())
    val connectionModel = GraphConnectionModel(GraphConnection())

    override val root = vbox {
        form {
            fieldset("State") {
                field("Name") {
                    textfield(nodeModel.name) {
                        required()
                    }
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

    protected fun saveChanges() {
        saveNode()
        saveConnection()
    }
    open fun saveNode() {
        nodeModel.commit()
    }
    open fun saveConnection() {
        connectionModel.commit()
    }
}