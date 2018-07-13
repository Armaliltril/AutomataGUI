package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.signals.AutomataNameBox
import com.example.demo.signals.AutomataTypeBox
import com.example.demo.signals.AutomataXCoordinateBox
import com.example.demo.signals.AutomataYCoordinateBox
import tornadofx.*

class AutomataStateEditor: View() {
    val nameField = FieldWithDescription("Name:", AutomataNameBox())
    val typeField = FieldWithDescription("Type:", AutomataTypeBox())
    val xCoordinateField = FieldWithDescription("X Coordinate:", AutomataXCoordinateBox())
    val yCoordinateBox = FieldWithDescription("Y Coordinate:", AutomataYCoordinateBox())

    override val root = vbox {
        add(nameField)
        add(typeField)
        add(xCoordinateField)
        add(yCoordinateBox)

        addClass(Styles.stateEditor)
    }
}