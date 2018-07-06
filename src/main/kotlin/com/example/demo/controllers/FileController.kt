package com.example.demo.controllers

import com.example.demo.signals.FilesToOpenSignal
import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class FileController:  Controller() {
    var files = listOf<File>()

    private val extensions = arrayOf(FileChooser.ExtensionFilter("Automates", "*.json"))
    private val openMode = FileChooserMode.Single

    fun openFiles() {
        files = chooseFile("Select File", extensions, openMode)
        fire(FilesToOpenSignal(files))
    }
    fun makeFile(filename: String) {
        // TODO
        println("Virtual $filename file created")
    }
    fun makeProject() {

    }

}