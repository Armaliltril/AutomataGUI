package com.example.demo.viewModel.automata

import com.example.demo.viewModel.GraphConnectionModel
import tornadofx.*

class ConnectionModel(connection: Connection): GraphConnectionModel(connection) {
    var type = bind { connection.typeProperty }
}