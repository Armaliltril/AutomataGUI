package com.example.demo.view

import tornadofx.*

class AutomataWorkingField: WorkingField() {

    override val stateEditor = find(AutomataStateEditor::class)
}