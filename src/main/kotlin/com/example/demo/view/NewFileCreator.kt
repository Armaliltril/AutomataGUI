package com.example.demo.view

import com.example.demo.signals.NewFileSignal
import tornadofx.*

class NewFileCreator: View() {

    override val root = vbox {
        label("Enter name of a new file")
        textfield {
            action {
                fire(NewFileSignal(text))
                close()
            }
        }
    }
}