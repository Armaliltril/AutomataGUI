package com.example.demo.view

import com.example.demo.app.Styles
import javafx.scene.Parent
import javafx.scene.control.Label
import javafx.scene.input.MouseEvent
import tornadofx.*

class OpenedFiles: View() {

    override val root = hbox {
        for (i in 0..5)
            add(FileLabel("Sample $i"))

        spacing = 1.0
    }

    private class FileLabel(title: String): Fragment() {

        init {
            this.title = title
        }

        override val root = label(title) {
            addEventFilter(MouseEvent.MOUSE_CLICKED, ::showChosenFile)
            useMaxWidth = true
            addClass(Styles.fileLabel)
        }

        private fun showChosenFile(evt: MouseEvent) {
            // TODO()
            println("File $title chosen")
        }

    }
}
