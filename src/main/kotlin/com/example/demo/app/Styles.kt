package com.example.demo.app

import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val automataState by cssclass()
        val menu by cssclass()
        val menuItem by cssclass()
    }

    init {
        automataState {
            fill = Color.RED
            borderColor += box(Color.BLACK)
            borderWidth += box(2.px)
        }
        menu and menuItem {
            fill = Color.WHITE
            fontSize = 17.px
            fontWeight = FontWeight.SEMI_BOLD
        }
    }
}