package com.example.demo.signals

import tornadofx.*

class AutomataXCoordinateBox(val value: Double): FXEvent(EventBus.RunOn.ApplicationThread)