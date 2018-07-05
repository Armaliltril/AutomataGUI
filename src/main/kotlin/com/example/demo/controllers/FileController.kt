package com.example.demo.controllers

import javafx.stage.FileChooser
import tornadofx.*
import java.io.File

class FileController:  Controller() {
    var files = listOf<File>()

    private val extensions = arrayOf(FileChooser.ExtensionFilter("Automates", ".*aut"))
    private val openMode = FileChooserMode.Single

    fun chooseFiles() {
        files = tornadofx.chooseFile("Select File", extensions, openMode)
    }
    fun openFile() {
        //TODO
    }
    fun makeFile() {
        //TODO
    }
    fun makeProject() {

    }

}