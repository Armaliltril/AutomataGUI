package com.example.demo.signals

import tornadofx.*
import java.io.File

class FilesToOpenSignal(val filenames: List<File>): FXEvent(EventBus.RunOn.ApplicationThread)