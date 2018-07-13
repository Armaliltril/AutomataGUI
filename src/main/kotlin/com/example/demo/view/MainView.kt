package com.example.demo.view

import com.example.demo.app.Styles
import tornadofx.*

class MainView : View() {
    private val topMenu: TopMenu by inject()
    private val workingField: WorkingField by inject()
    private val automataStateEditor: AutomataStateEditor by inject()

    override val root = vbox {
        add(topMenu)
        hbox {
            add(workingField)
            vbox {
                rectangle {
                    height = 200.0
                }
                add(automataStateEditor)
            }
        }
    }
}