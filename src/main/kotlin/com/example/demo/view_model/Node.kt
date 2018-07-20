package com.example.demo.view_model

import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

abstract class Node: Circle() {
    val connections = listOf<Connection>()
}