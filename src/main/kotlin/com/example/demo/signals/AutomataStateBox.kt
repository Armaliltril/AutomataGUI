package com.example.demo.signals

import tornadofx.*

abstract class AutomataStateBox(var state: Any = "Default"): FXEvent(EventBus.RunOn.ApplicationThread)
