package com.example.demo.viewModel

import tornadofx.*

open class GraphConnectionModel(var connection: GraphConnection): ViewModel() {
    val startNode = bind { connection.startNodeProperty }
    val endNode = bind {connection.endNodeProperty }
}