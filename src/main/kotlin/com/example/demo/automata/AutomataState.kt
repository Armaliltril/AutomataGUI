package com.example.demo.automata

data class AutomataState(var name: String,
                         var type: QueueType) {

    enum class QueueType {
        INITIAL,
        GENERAL,
        FINAL;
    }
}