package com.example.demo.viewModel.automata

import com.example.demo.viewModel.GraphConnectionModel
import tornadofx.*

class ConnectionModel(connection: Connection): GraphConnectionModel(connection) {
    val type = bind { connection.typeProperty }
}