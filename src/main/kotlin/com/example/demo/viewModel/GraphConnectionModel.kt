package com.example.demo.viewModel

import tornadofx.*

open class GraphConnectionModel(var connection: GraphConnection): ViewModel() {
    var startNode = bind { connection.startNodeProperty }
    var endNode = bind {connection.endNodeProperty }
}