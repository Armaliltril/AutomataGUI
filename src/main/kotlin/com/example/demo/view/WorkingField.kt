package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.signals.*
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

class WorkingField : Fragment() {

    private val circleRadius = 25.0

    private val toolboxItems = mutableListOf<Node>()
    private val workingFieldItems = mutableListOf<Node>()

    private var movingNode: Node? = null

    private var toolbox: Parent by singleAssign()
    private var workArea: Pane by singleAssign()

    /*private fun nameLister(node: StateNode): (AutomataNameBox) -> Unit = {
        node.automataState.name = it.name
    }*/

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
                            movingNode = createDefaultStateNode()
                            //It's "kostyl" but works perfect ;)
                            workArea.add(movingNode!!)
                            movingNode!!.relocate(-1000.0, -1000.0)
                        }
                    }

        workingFieldItems.firstOrNull {
                            val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
                            it.contains(mousePt)
            }
                         .apply {
                            if (this != null) {
                                when {
                                    evt.isShiftDown -> startDragging(this)
                                    else -> selectNode(this)
                                }
                            }
                    }
    }
    private fun animateDrag(evt : MouseEvent) {

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if( workArea.contains(mousePt) && movingNode != null ) {
            movingNode!!.relocate( mousePt.x, mousePt.y )
        }
    }
    private fun stopDrag(evt : MouseEvent) {

        if (movingNode != null) {
            val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
            (movingNode!! as StateNode).apply {
                xCoordinate = mousePt.x
                yCoordinate = mousePt.y
            }

            workingFieldItems.add(movingNode!!)
        }

        movingNode = null
    }

    private fun selectNode(node: Node) {
        fire(AutomataStateBox(node as StateNode))
        unsubscribeNodesFromEditor()
        subscribeNodeToStateEditor(node)
        removeStyleFromNodes(Styles.chosenAutomataState)
        node.addClass(Styles.chosenAutomataState)
    }
    private fun startDragging(node: Node) {
        movingNode = node
        workingFieldItems.remove(node)
    }

    private fun createDefaultStateNode() = StateNode().apply {
        radius = circleRadius
    }
    private fun subscribeNodeToStateEditor(node: StateNode) {
        subscribe<AutomataNameBox> { node.automataState.name = it.name }
        subscribe<AutomataTypeBox> { node.automataState.type = it.type }
        subscribe<AutomataXCoordinateBox> {
            node.xCoordinate = it.value
            node.relocate(node.xCoordinate, node.yCoordinate)
        }
        subscribe<AutomataYCoordinateBox> {
            node.yCoordinate = it.value
            node.relocate(node.xCoordinate, node.yCoordinate)
        }
    }
    private fun unsubscribeNodesFromEditor() {
        workingFieldItems.forEach {
            it.apply {
                unsubscribe<AutomataNameBox> {  }
                unsubscribe<AutomataTypeBox> {  }
                unsubscribe<AutomataXCoordinateBox> {  }
                unsubscribe<AutomataYCoordinateBox> {  }
            }
        }
    }
    private fun removeStyleFromNodes(styleClass: CssRule) {
    workingFieldItems.forEach {
        if (it.hasClass(styleClass))
            it.removeClass(styleClass)
    }
}

}
