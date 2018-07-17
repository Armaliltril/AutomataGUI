package com.example.demo.signals

import tornadofx.*

class AutomataNameBox(var name: String): FXEvent(EventBus.RunOn.ApplicationThread)