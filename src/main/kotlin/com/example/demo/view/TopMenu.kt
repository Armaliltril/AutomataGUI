package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controllers.FileController
import javafx.scene.paint.Color
import tornadofx.*

class TopMenu : View() {
    private val fileController = FileController()

    override val root = menubar {
        menu("File") {
            menu("New") {
                item("Project") {
                    action {
                        fileController.makeProject()
                    }
                    addClass(Styles.menuItem)
                }
                item("File") {
                    action {
                        fileController.makeFile()
                    }
                    addClass(Styles.menuItem)
                }
                addClass(Styles.menu)
            }
            item("Open") {
                action {
                    fileController.chooseFiles()
                }
                addClass(Styles.menuItem)
            }
            addClass(Styles.menu)
        }
        menu("View") {
            item("Tool Bar 1") {
                action {}
                addClass(Styles.menuItem)
            }
            item("Tool Bar 2") {
                action {}
                addClass(Styles.menuItem)
            }
            item("Tool Bar 3") {
                action {}
                addClass(Styles.menuItem)
            }
            addClass(Styles.menu)
        }
        menu("Tools") {
            item("Sort") {
                action {
                    // TODO()
                    println("Virtual Sorting")
                }
                addClass(Styles.menuItem)
            }
            addClass(Styles.menu)
        }
        style {
            fill = Color.WHITE
        }
    }
}
