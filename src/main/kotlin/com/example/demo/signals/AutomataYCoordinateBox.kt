package com.example.demo.signals

import tornadofx.*

class AutomataYCoordinateBox(val value: Double): FXEvent(EventBus.RunOn.ApplicationThread)