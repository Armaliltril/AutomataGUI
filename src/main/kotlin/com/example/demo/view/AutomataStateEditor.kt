package com.example.demo.view

import com.example.demo.automata.AutomataState
import com.example.demo.automata.Transaction
import com.example.demo.signals.AutomataNameBox
import com.example.demo.signals.AutomataTypeBox
import com.example.demo.signals.AutomataXCoordinateBox
import com.example.demo.signals.AutomataYCoordinateBox
import tornadofx.*

class AutomataStateEditor: View() {

    private var CELLHEIGHT = 25.0

    override val root = vbox {
        form {
            fieldset {
                field("Name") {
                    textfield {
                        action {
                            fire(AutomataNameBox(text))
                        }
                    }
                }
                field("Type") {
                    listview<AutomataState.QueueType> {
                        AutomataState.QueueType.values().forEach { items.add(it) }
                        prefHeight = items.size * CELLHEIGHT

                        onUserSelect {
                            fire(AutomataTypeBox(it))
                        }
                    }
                }
                field("X Coordinate") {
                    textfield {
                        // TODO: Add filter
                        action {
                            fire(AutomataXCoordinateBox(text.toDouble()))
                        }
                    }
                }
                field("Y Coordinate") {
                    textfield {
                        // TODO: Add filter
                        action {
                            fire(AutomataYCoordinateBox(text.toDouble()))
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
                        prefHeight = items.size * CELLHEIGHT
                    }
                }
                field("Condition") {

                }
                field("Connected With") {

                }
            }

}

//TODO: Add subscribes