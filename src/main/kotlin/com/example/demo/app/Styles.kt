package com.example.demo.app

import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val automataState by cssclass()
        val movingAutomataState by cssclass()
        val chosenAutomataState by cssclass()

        val menu by cssclass()
        val menuItem by cssclass()
        val fileLabel by cssclass()
        val stateEditor by cssclass()
    }

    init {
        automataState {
            borderColor += box(Color.BLACK)
            borderWidth += box(2.px)
        }
        movingAutomataState {
            borderColor += box(Color.BLACK)
            borderWidth += box(2.px)
            effect = DropShadow()
            opacity = 0.7
        }
        chosenAutomataState {
            borderColor += box(Color.BLACK)
            borderWidth += box(2.px)
            effect = javafx.scene.effect.InnerShadow()
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
        stateEditor {
            backgroundColor += Color.WHITE
            borderColor += box(Color.BLACK)
            borderWidth += box(1.px)
            padding = box(2.px)
        }
    }
}