package com.example.demo.view

import com.example.demo.app.Styles
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.effect.DropShadow
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

class WorkingField : View() {

    private val circleRadius = 25.0

    private var isNodeMoving = false
    private var isNodeSelected = false
    private var isNodeFromField = false

    private val toolboxItems = mutableListOf<Node>()
    private val workingFieldItems = mutableListOf<Node>()

    private var movingCircle: Circle by singleAssign()

    private var toolbox: Parent by singleAssign()

    private var workArea: Pane by singleAssign()

    override val root = vbox {

        hbox {

            toolbox = vbox {

                add(createCircle())

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

                    // When draqgging we actually operate 'singletone' circle, hiding it when dragging is over
                    movingCircle = circle(radius = circleRadius) {
                        addClass(Styles.automataState)
                        isVisible = false
                        opacity = 0.7
                        effect = DropShadow()
                    }
                    add(movingCircle)
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

            addEventFilter(MouseEvent.MOUSE_PRESSED, ::startDrag)
            addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
            addEventFilter(MouseEvent.MOUSE_EXITED, ::stopDrag)
            addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)
            addEventFilter(MouseEvent.MOUSE_RELEASED, ::drop)

            shortcut("Shift+C") {
                // fireEvent()
            }
        }

        style {
            setPrefSize(1200.0, 800.0)
            spacing = 10.px
        }
    }

    init {
        toolboxItems.addAll( toolbox.childrenUnmodifiable )
    }

    private fun startDrag(evt : MouseEvent) {

        toolboxItems.firstOrNull {
                        val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
             it.contains(mousePt)
                    }
                    .apply {
                        if( this != null ) {
                            isNodeMoving = true
                            isNodeSelected = true
                            isNodeFromField = false
                        }
                    }

        workingFieldItems.firstOrNull {
                            val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
                 it.contains(mousePt)
                         }
                         .apply {
                             if (this != null) {
                                 isNodeMoving = true
                                 isNodeSelected = true
                                 isNodeFromField = true
                                 this.removeFromParent()
                                 workingFieldItems.remove(this)
                                }
                         }

    }

    private fun animateDrag(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if( workArea.contains(mousePt) ) {

            if( !movingCircle.isVisible && isNodeSelected)
                movingCircle.isVisible = true

            movingCircle.relocate( mousePt.x, mousePt.y )
        }

    }

    private fun stopDrag(evt : MouseEvent) {

        if( movingCircle.isVisible )
            movingCircle.isVisible = false

    }

    private fun drop(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        val isThereNoOtherItem = workingFieldItems.firstOrNull { it.contains(mousePt) } == null
        println(workingFieldItems)
        if( workArea.contains(mousePt) && isThereNoOtherItem) {
            if (isNodeMoving) {
                val newCircle = createCircle()
                workArea.add( newCircle )
                workingFieldItems.add(newCircle)
                newCircle.relocate( mousePt.x, mousePt.y )

                movingCircle.toFront()

            }
        }

        isNodeMoving = false
        isNodeSelected = false

    }

    private fun createCircle() = Circle().apply {
        radius = circleRadius
        addClass(Styles.automataState)
    }

}
