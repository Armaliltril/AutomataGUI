package com.example.demo.view

import com.example.demo.automata.Connection
import tornadofx.*

class ConnectionEditor(connection: Connection) : View() {
    override val root = hbox {
        label("Virtual connection view")
    }
}
