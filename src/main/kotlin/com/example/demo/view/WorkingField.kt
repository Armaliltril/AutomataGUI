package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.viewModel.Connection
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import com.example.demo.viewModel.StateNode
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import tornadofx.*

class WorkingField : Fragment() {

    private var toolbox: Parent by singleAssign()
    private var workArea: Pane by singleAssign()
    private val automataStateEditor = find(AutomataStateEditor::class)

    private val toolboxItems = mutableListOf<Node>()
    private val workAreaNodes = mutableListOf<Node>()

    private var movingNode: Node? = null
    private var movingLine: Line? = null

    override val root = vbox {

        hbox {

            toolbox = vbox {

                add(StateNode())

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
            add(automataStateEditor)

            addEventFilter(MouseEvent.MOUSE_PRESSED, ::pressNode)
            addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
            addEventFilter(MouseEvent.MOUSE_EXITED, ::stopDrag)
            addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)

            vboxConstraints {
                vgrow = Priority.ALWAYS
            }

            padding = Insets(10.0)
            spacing = 10.0
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

        toolboxItems.getItemUnderMouse(evt)
                    .apply {
                        if( this != null ) {
                            movingNode = StateNode()
                            workArea.add(movingNode!!)
                            movingNode!!.addClass(Styles.movingAutomataState)
                            //It's "kostyl" but works perfect ;)
                            movingNode!!.relocate(-1000.0, -1000.0)
                        }
                    }

        workAreaNodes.getItemUnderMouse(evt)
                         .apply {
                            if (this != null) {
                                when {
                                    evt.isShiftDown -> startDragging(this)
                                    evt.isControlDown -> startConnection(this as StateNode)
                                    else -> selectNode(this as StateNode)
                                }
                            }
                        }

    }
    private fun animateDrag(evt : MouseEvent) {

        val mousePt = workArea.getMousePosition(evt)

        if (workArea.contains(mousePt) && movingNode != null) {
            val radius = getMovingNodeRadius()
            (movingNode as Circle).apply {
                centerX = mousePt.x
                centerY = mousePt.y
                relocate(centerX - radius, centerY - radius)
            }

        }
        else if (workArea.contains(mousePt) && movingLine != null) {
            movingLine!!.apply {
                endX = mousePt.x
                endY = mousePt.y
            }
        }
    }
    private fun stopDrag(evt : MouseEvent) {

        if (movingNode != null) {
            placeMovingNodeToGround(evt)
        }
        else if (movingLine != null){
            val node = workAreaNodes.getItemUnderMouse(evt)

            when(node) {
                null -> endOfLineLandedOnNothing()
                else -> (node as StateNode).bindEndOfLine()
            }
        }

        movingLine = null
        movingNode = null
    }

    private fun selectNode(selectedNode: StateNode) {
        automataStateEditor.stateModel.rebind { node = selectedNode }
        removeStyleFromNodes(Styles.chosenAutomataState)
        selectedNode.addClass(Styles.chosenAutomataState)
        //DEBUG
        println(selectedNode)
    }
    private fun startConnection(node: StateNode) {
        movingLine = node.startNewConnection()
        node.connections.add(movingLine as Connection)
        workArea.add(movingLine!!)
    }
    private fun startDragging(node: Node) {
        movingNode = node
        movingNode!!.addClass(Styles.movingAutomataState)
        workAreaNodes.remove(node)
    }

    private fun StateNode.bindEndOfLine() {
        val node = this
        (movingLine as Connection).apply {
            endNodeProperty.value = node
            endXProperty().bindBidirectional(node.centerXProperty())
            endYProperty().bindBidirectional(node.centerYProperty())
        }
        node.connections.add(movingLine as Connection)
    }
    private fun endOfLineLandedOnNothing() {
        (movingLine as Connection).apply {
            startNodeProperty.value.connections.remove(this)
            removeFromParent()
        }
    }
    private fun placeMovingNodeToGround(evt: MouseEvent) {
        val mousePt = workArea.getMousePosition(evt)
        println(mousePt)

        (movingNode as StateNode).apply {
            centerX = mousePt.x
            centerY = mousePt.y
        }

        movingNode!!.removeClass(Styles.movingAutomataState)
        workAreaNodes.add(movingNode!!)
    }

    private fun StateNode.startNewConnection() = Connection().apply {
        val node = this@startNewConnection
        startNodeProperty.value = node
        startXProperty().bindBidirectional(node.centerXProperty())
        startYProperty().bindBidirectional(node.centerYProperty())
    }
    private fun Collection<Node>.getItemUnderMouse(evt: MouseEvent): Node? {
        return this.firstOrNull {
            val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
            it.contains(mousePt.x, mousePt.y)
        }
    }
    private fun Pane.getMousePosition(evt: MouseEvent) = sceneToLocal(evt.sceneX, evt.sceneY)
    private fun getMovingNodeRadius() = (movingNode as Circle).radius

    private fun removeStyleFromNodes(styleClass: CssRule) {
    workAreaNodes.forEach {
        if (it.hasClass(styleClass))
            it.removeClass(styleClass)
    }
}
}
