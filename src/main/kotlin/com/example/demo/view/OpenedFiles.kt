package com.example.demo.view

import com.example.demo.view.automata.AutomataWorkingField
import tornadofx.*

// TODO: I Should do it with tabpane and include WorkingField include each tab

class OpenedFiles: View() {

    override val root = tabpane {
        for (i in 0..5)
            tab("Sample $i") {
                add(find(AutomataWorkingField::class))
            }

    }
}
