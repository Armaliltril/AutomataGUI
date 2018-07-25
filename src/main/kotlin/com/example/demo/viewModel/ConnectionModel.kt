package com.example.demo.viewModel

import tornadofx.*

class ConnectionModel(var connection: Connection): ViewModel() {
    val type = bind { connection.typeProperty }
    val startNode = bind { connection.startNodeProperty }
    val endNode = bind {connection.endNodeProperty }
}