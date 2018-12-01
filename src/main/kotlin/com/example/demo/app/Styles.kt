package com.example.demo.app

import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val graphNode by cssclass()
        val selected by cssclass()
        val moving by cssclass()

        val automataState by cssclass()

        val menu by cssclass()
        val menuItem by cssclass()
        val fileLabel by cssclass()
        val stateEditor by cssclass()
    }

    init {
        graphNode {
            borderColor += box(Color.BLACK)
            borderWidth += box(3.px)
            fill = Color.GOLD

            and(automataState) {
                fill = Color.RED
            }
        }
        moving {
            effect = DropShadow()
            opacity = 0.7
        }
        selected {
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