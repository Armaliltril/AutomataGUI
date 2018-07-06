package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val automataState by cssclass()
        val menu by cssclass()
        val menuItem by cssclass()
        val fileLabel by cssclass()
    }

    init {
        automataState {
            fill = Color.RED
            borderColor += box(Color.BLACK)
            borderWidth += box(2.px)
        }
        menu and menuItem {
            backgroundColor += Color.WHITE
            fontSize = 17.px
            fontWeight = FontWeight.SEMI_BOLD
        }
        fileLabel {
            backgroundColor += Color.WHITE
            borderColor += box(Color.BLACK)
            borderWidth += box(1.px)
            padding = box(2.px)
        }
    }
}