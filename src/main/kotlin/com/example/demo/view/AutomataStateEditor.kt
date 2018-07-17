package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.automata.AutomataState
import com.example.demo.signals.AutomataNameBox
import com.example.demo.signals.AutomataTypeBox
import com.example.demo.signals.AutomataXCoordinateBox
import com.example.demo.signals.AutomataYCoordinateBox
import tornadofx.*

class AutomataStateEditor: View() {

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
                        prefHeight = items.size * 25.0
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
            add(getConnection())
            add(getConnection())
        }
    }

    private fun getConnection() =
            fieldset {
                field("Connection") {
                    textfield {

                    }
                }
            }
}