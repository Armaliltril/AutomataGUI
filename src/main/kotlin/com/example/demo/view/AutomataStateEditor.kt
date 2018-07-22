package com.example.demo.view

import com.example.demo.automata.AutomataState
import com.example.demo.automata.Transaction
import com.example.demo.signals.*
import tornadofx.*

class AutomataStateEditor: View() {

    private var cellHeight = 25.0

    override val root = vbox {
        form {
            fieldset {
                field("Name") {
                    textfield {
                        action {
                            fire(AutomataNameBox(text))
                        }
                        subscribe<AutomataStateBox> { text = it.state.automataState.name }
                    }
                }
                field("Type") {
                    listview<AutomataState.QueueType> {
                        AutomataState.QueueType.values().forEach { items.add(it) }
                        prefHeight = items.size * cellHeight

                        onUserSelect {
                            fire(AutomataTypeBox(it))
                        }

                        subscribe<AutomataStateBox> { /*TODO*/ }
                    }
                }
                field("X Coordinate") {
                    textfield {
                        // TODO: Add filter
                        action {
                            fire(AutomataXCoordinateBox(text.toDouble()))
                        }
                        subscribe<AutomataStateBox> { text = it.state.xCoordinate.toString() }
                    }
                }
                field("Y Coordinate") {
                    textfield {
                        // TODO: Add filter
                        action {
                            fire(AutomataYCoordinateBox(text.toDouble()))
                        }
                        subscribe<AutomataStateBox> { text = it.state.yCoordinate.toString() }
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

}

//TODO: Add subscribes