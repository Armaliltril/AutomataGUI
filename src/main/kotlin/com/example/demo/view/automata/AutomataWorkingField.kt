package com.example.demo.view.automata

import com.example.demo.view.WorkingField

class AutomataWorkingField: WorkingField() {

    override val stateEditor = find(AutomataStateEditor::class)
}