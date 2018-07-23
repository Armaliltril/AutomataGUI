package com.example.demo.viewModel

import tornadofx.*

abstract class Connection(var startNode: GraphNode, var endNode: GraphNode): ViewModel() {
    enum class MessageType {
        PRIVATE,
        SHARED,
        SENDING,
        RECEIVING
    }
}