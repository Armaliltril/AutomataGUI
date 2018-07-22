package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.controllers.FileController
import com.example.demo.signals.NewFileSignal
import javafx.scene.paint.Color
import tornadofx.*

class TopMenu : View() {
    private val fileController = FileController()

    override val root = menubar {
        menu("File") {
            menu("New") {
                item("Project") {
                    // TODO
                    action {
                        fileController.makeProject()
                    }
                    addClass(Styles.menuItem)
                }
                item("File") {
                    var filename: String

                    subscribe<NewFileSignal> {
                        filename = it.filename
                        fileController.makeFile(filename)
                    }

                    action {
                        find(NewFileCreator::class).openModal()
                    }

                    addClass(Styles.menuItem)
                }
                addClass(Styles.menu)
            }
            item("Open") {
                action {
                    fileController.openFiles()
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
