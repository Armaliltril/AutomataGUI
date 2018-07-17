package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.signals.AutomataNameBox
import com.example.demo.signals.AutomataTypeBox
import com.example.demo.signals.AutomataXCoordinateBox
import com.example.demo.signals.AutomataYCoordinateBox
import tornadofx.*

class AutomataStateEditor: View() {
    private val nameField = FieldWithDescription("Name:", AutomataNameBox())
    private val typeField = FieldWithDescription("Type:", AutomataTypeBox())
    private val xCoordinateField = FieldWithDescription("X Coordinate:", AutomataXCoordinateBox())
    private val yCoordinateBox = FieldWithDescription("Y Coordinate:", AutomataYCoordinateBox())

    override val root = vbox {
        add(nameField)
        add(typeField)
        add(xCoordinateField)
        add(yCoordinateBox)

        addClass(Styles.stateEditor)
    }
}