package com.example.demo.signals

import com.example.demo.view_model.StateNode
import tornadofx.*

class AutomataStateBox(val state: StateNode): FXEvent(EventBus.RunOn.ApplicationThread)
