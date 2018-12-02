package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.viewModel.GraphConnection
import com.example.demo.viewModel.GraphNode
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.*

open class WorkingField : Fragment() {

    private val toolbox = vbox {

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
    private val workArea = pane {

        anchorpaneConstraints {
            leftAnchor = 0.0
            topAnchor = 0.0
            rightAnchor = 0.0
            bottomAnchor = 0.0
        }
    }

    open val stateEditor = find(StateEditor::class)

    open val toolboxItems = listOf<Node>(GraphNode())
    private val workAreaNodes = mutableListOf<Node>()

    protected var movingNode: GraphNode? = null
    protected var movingConnection: GraphConnection? = null

    override val root = hbox {
        add(toolbox)
        anchorpane {
            add(workArea)

            hboxConstraints {
                hgrow = Priority.ALWAYS
            }
        }
        //BUG
        add(stateEditor)

        addEventFilter(MouseEvent.MOUSE_PRESSED, ::pressNode)
        addEventFilter(MouseEvent.MOUSE_DRAGGED, ::animateDrag)
        addEventFilter(MouseEvent.MOUSE_EXITED, ::stopDrag)
        addEventFilter(MouseEvent.MOUSE_RELEASED, ::stopDrag)

        vboxConstraints {
            vgrow = Priority.ALWAYS
        }

        padding = Insets(10.0)
        spacing = 10.0
        setPrefSize(1200.0, 800.0)
    }

    init {
        fillToolbox()
    }

    protected fun fillToolbox() {
        toolboxItems.forEach { toolbox.add(it) }
    }

    private fun pressNode(evt : MouseEvent) {

        toolboxItems.getItemUnderMouse(evt)
                    .apply {
                        if( this != null ) {
                            movingNode = GraphNode()
                            workArea.add(movingNode!!)
                            movingNode!!.addClass(Styles.moving)
                            //It's "kostyl" but works perfect ;)
                            movingNode!!.relocate(-1000.0, -1000.0)
                        }
                    }

        workAreaNodes.getItemUnderMouse(evt)
                         .apply {
                            if (this != null) {
                                if (this is GraphNode) {
                                    when {
                                        evt.isShiftDown -> startDragging(this)
                                        evt.isControlDown -> startConnection(this)
                                        else -> selectNode(this)
                                    }
                                }
                                else if (this is GraphConnection) {
                                    //TODO(Select connection)
                                    println("Selected connection")
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
        else if (workArea.contains(mousePt) && movingConnection != null) {
            movingConnection!!.apply {
                endX = mousePt.x
                endY = mousePt.y
            }
        }
    }
    private fun stopDrag(evt : MouseEvent) {

        if (movingNode != null) {
            placeMovingNodeToGround(evt)
        }
        else if (movingConnection != null){
            val node = workAreaNodes.getItemUnderMouse(evt)

            when(node) {
                null -> endOfLineLandedOnNothing()
                else -> (node as GraphNode).bindEndOfLine()
            }
        }

        movingConnection = null
        movingNode = null
    }

    private fun selectNode(selectedNode: GraphNode) {
        stateEditor.nodeModel.rebind { node = selectedNode }
        removeStyleFromNodes(Styles.selected)
        selectedNode.addClass(Styles.selected)
    }
    private fun startConnection(node: GraphNode) {
        movingConnection = node.startNewConnection()
        node.connections.add(movingConnection!!)
        workArea.add(movingConnection!!)
    }
    private fun startDragging(node: GraphNode) {
        movingNode = node
        movingNode!!.addClass(Styles.moving)
        workAreaNodes.remove(node)
    }

    private fun GraphNode.bindEndOfLine() {
        val node = this
        movingConnection!!.apply {
            endNodeProperty.value = node
            endXProperty().bindBidirectional(node.centerXProperty())
            endYProperty().bindBidirectional(node.centerYProperty())
        }
        node.connections.add(movingConnection!!)
    }
    private fun endOfLineLandedOnNothing() {
        movingConnection!!.apply {
            startNodeProperty.value.connections.remove(this)
            removeFromParent()
        }
    }
    private fun placeMovingNodeToGround(evt: MouseEvent) {
        val mousePt = workArea.getMousePosition(evt)

        movingNode!!.apply {
            centerX = mousePt.x
            centerY = mousePt.y
        }

        movingNode!!.removeClass(Styles.moving)
        workAreaNodes.add(movingNode!!)
    }

    private fun GraphNode.startNewConnection() = GraphConnection().apply {
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
    private fun getMovingNodeRadius() = movingNode!!.radius

    private fun removeStyleFromNodes(styleClass: CssRule) {
    workAreaNodes.forEach {
        if (it.hasClass(styleClass))
            it.removeClass(styleClass)
    }
}
}
