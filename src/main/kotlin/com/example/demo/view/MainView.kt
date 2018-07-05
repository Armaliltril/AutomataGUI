package com.example.demo.view

import com.example.demo.app.Styles
import tornadofx.*

class MainView : View() {
    private val topMenu: TopMenu by inject()
    private val workingField: WorkingField by inject()

    override val root = vbox {
        add(topMenu)
        add(workingField)
    }
}