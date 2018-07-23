package com.example.demo.view

import tornadofx.*

class MainView : View() {
    private val topMenu: TopMenu by inject()
    private val openedFiles: OpenedFiles by inject()

    override val root = vbox {
        add(topMenu)
        add(openedFiles)
    }
}