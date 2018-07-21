package com.example.demo.view

import com.example.demo.app.Styles
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import com.example.demo.view_model.StateNode
import tornadofx.*

class WorkingField : View() {

    private val circleRadius = 25.0

    private val toolboxItems = mutableListOf<Node>()
    private val workingFieldItems = mutableListOf<Node>()

    private var movingCircle: Node? = null
    private var toolbox: Parent by singleAssign()
    private var workArea: Pane by singleAssign()

    override val root = vbox {

        hbox {

            toolbox = vbox {

                add(createDefaultStateNode())

                // In case of other objects
                spacing = 10.0
                padding = Insets(10.0)
                alignment = Pos.CENTER

                hboxConstraints {
                    hgrow = Priority.NEVER
                }
                style {
                    backgroundColor += Color.WHITE
                    borderColor += box(Color.BLACK)
                }
            }
            anchorpane {
                workArea = pane {

                    anchorpaneConstraints {
                        leftAnchor = 0.0
                        topAnchor = 0.0
                        rightAnchor = 0.0
                        bottomAnchor = 0.0
                    }
                }

                hboxConstraints {
                    hgrow = Priority.ALWAYS
                }
            }

            vboxConstraints {
                vgrow = Priority.ALWAYS
            }

            padding = Insets(10.0)
            spacing = 10.0

            addEventFilter(MouseEvent.MOUSE_PRESSED, ::pressNode)
            addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
            addEventFilter(MouseEvent.MOUSE_EXITED, ::stopDrag)
            addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)
        }

        style {
            setPrefSize(1200.0, 800.0)
            spacing = 10.px
        }
    }

    init {
        toolboxItems.addAll( toolbox.childrenUnmodifiable )
    }

    private fun pressNode(evt : MouseEvent) {

        toolboxItems.firstOrNull {
                        val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
             it.contains(mousePt)
                    }
                    .apply {
                        if( this != null ) {
                            println("Toolbox is not null!")
                            movingCircle = createDefaultStateNode()
                            //It's "kostyl" but works perfect ;)
                            workArea.add(movingCircle!!)
                            movingCircle!!.relocate(-1000.0, -1000.0)
                        }
                    }

        workingFieldItems.firstOrNull {
                            val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
                            it.contains(mousePt)
            }
                         .apply {
                            if (this != null) {
                                println("WPRKFIELD Items is not null!")
                                when {
                                    evt.isShiftDown -> startDragging(this)
                                    else -> selectNode(this)
                                }
                            }
                    }


    }
    private fun animateDrag(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if( workArea.contains(mousePt) && movingCircle != null ) {
            movingCircle!!.relocate( mousePt.x, mousePt.y )
        }
    }
    private fun stopDrag(evt : MouseEvent) {

        if (movingCircle != null)
            workingFieldItems.add(movingCircle!!)

        movingCircle = null
    }

    private fun selectNode(node: Node) {
        removeStyleFromNodes(Styles.chosenAutomataState)
        node.addClass(Styles.chosenAutomataState)
    }
    private fun startDragging(node: Node) {
        movingCircle = node
    }

    private fun createDefaultStateNode() = StateNode().apply {
        radius = circleRadius
    }

    private fun removeStyleFromNodes(styleClass: CssRule) {
        workingFieldItems.forEach {
            if (it.hasClass(styleClass))
                it.removeClass(styleClass)
        }
    }

}
