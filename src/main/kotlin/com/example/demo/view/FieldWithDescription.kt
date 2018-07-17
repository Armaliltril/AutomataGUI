package com.example.demo.view

import com.example.demo.signals.AutomataStateBox
import tornadofx.*

class FieldWithDescription(description: String, val stateBox: AutomataStateBox): View() {
    private val descriptionLabel = label(description)
    private val value = textfield {
        action {
            fillStateBox()
            fire(stateBox)
        }
    }

    override val root = hbox {
        add(descriptionLabel)
        add(value)

        spacing = 10.0
    }

    private fun fillStateBox() {
        stateBox.state = value.text
    }
}