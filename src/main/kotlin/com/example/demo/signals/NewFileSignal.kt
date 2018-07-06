package com.example.demo.signals

import tornadofx.*

class NewFileSignal(val filename: String): FXEvent(EventBus.RunOn.ApplicationThread)