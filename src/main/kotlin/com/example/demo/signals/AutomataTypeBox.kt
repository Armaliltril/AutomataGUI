package com.example.demo.signals

import com.example.demo.automata.AutomataState
import tornadofx.*

class AutomataTypeBox(val type: AutomataState.QueueType): FXEvent(EventBus.RunOn.ApplicationThread)