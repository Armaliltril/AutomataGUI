package com.example.demo.view

import com.example.demo.automata.AutomataState
import com.example.demo.automata.Transaction
import com.example.demo.view_model.StateNode
import tornadofx.*
import com.example.demo.view_model.StateNodeModel

class AutomataStateEditor: Fragment() {

    private var cellHeight = 25.0

    var stateModel = StateNodeModel(StateNode())

    override val root = vbox {
        form {
            fieldset {
                field("Name") {
                    textfield(stateModel.name) {
                        action { stateModel.commit() }
                    }
                }
                field("Type") {
                    listview<AutomataState.QueueType> {
                    //TODO
                    }
                }
                field("X Coordinate") {
                    textfield(stateModel.xCoordinate) {
                        action {
                            stateModel.commit()
                            relocate(stateModel.node)
                        }
                    }
                }
                field("Y Coordinate") {
                    textfield(stateModel.yCoordinate) {
                        action {
                            stateModel.commit()
                            relocate(stateModel.node)
                        }
                    }
                }
            }
        }
    }

    private fun getConnection(transaction: Transaction) =
            fieldset {
                field("Name") {
                    textfield(transaction.name) {

                    }
                }
                field("Type") {
                    listview<Transaction.MessageType> {
                        Transaction.MessageType.values().forEach { items.add(it) }
                        prefHeight = items.size * cellHeight
                    }
                }
                field("Condition") {

                }
                field("Connected With") {

                }
            }
    private fun relocate(node: StateNode) {
        node.relocate(node.xCoordinateProperty.value, node.yCoordinateProperty.value)
    }

}
