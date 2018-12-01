package com.example.demo.view

import com.example.demo.app.Styles
import com.example.demo.viewModel.GraphConnection
import com.example.demo.viewModel.GraphNode
import com.example.demo.viewModel.automata.Connection
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.Pane
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Line
import tornadofx.*

open class WorkingField : Fragment() {

    open val toolbox = vbox {

        add(GraphNode())

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
    open val workArea = pane {

        anchorpaneConstraints {
            leftAnchor = 0.0
            topAnchor = 0.0
            rightAnchor = 0.0
            bottomAnchor = 0.0
        }
    }

    open val stateEditor = find(StateEditor::class)

    private val toolboxItems = mutableListOf<Node>()
    private val workAreaNodes = mutableListOf<Node>()

    protected var movingNode: GraphNode? = null
    protected var movingConnection: GraphConnection? = null

    override val root = vbox {

        hbox {
            add(toolbox)
            anchorpane {
                add(workArea)

                hboxConstraints {
                    hgrow = Priority.ALWAYS
                }
            }
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
        }
        style {
            setPrefSize(1200.0, 800.0)
            spacing = 10.px
        }
    }

    init {
        toolboxItems.addAll( toolbox.childrenUnmodifiable )
    }

    fun pressNode(evt : MouseEvent) {

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
                            if (this != null && this is GraphNode) {
                                when {
                                    evt.isShiftDown -> startDragging(this)
                                    evt.isControlDown -> startConnection(this)
                                    else -> selectNode(this)
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
    fun stopDrag(evt : MouseEvent) {

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

    fun selectNode(selectedNode: GraphNode) {
        stateEditor.nodeModel.rebind { node = selectedNode }
        removeStyleFromNodes(Styles.selected)
        selectedNode.addClass(Styles.selected)
    }
    fun startConnection(node: GraphNode) {
        movingConnection = node.startNewConnection()
        node.connections.add(movingConnection as GraphConnection)
        workArea.add(movingConnection!!)
    }
    fun startDragging(node: GraphNode) {
        movingNode = node
        movingNode!!.addClass(Styles.moving)
        workAreaNodes.remove(node)
    }

    fun GraphNode.bindEndOfLine() {
        val node = this
        movingConnection!!.apply {
            endNodeProperty.value = node
            endXProperty().bindBidirectional(node.centerXProperty())
            endYProperty().bindBidirectional(node.centerYProperty())
        }
        node.connections.add(movingConnection!!)
    }
    fun endOfLineLandedOnNothing() {
        movingConnection!!.apply {
            startNodeProperty.value.connections.remove(this)
            removeFromParent()
        }
    }
    fun placeMovingNodeToGround(evt: MouseEvent) {
        val mousePt = workArea.getMousePosition(evt)

        movingNode!!.apply {
            centerX = mousePt.x
            centerY = mousePt.y
        }

        //Style issue
        movingNode!!.removeClass(Styles.moving)
        workAreaNodes.add(movingNode!!)
    }

    fun GraphNode.startNewConnection() = GraphConnection().apply {
        val node = this@startNewConnection
        startNodeProperty.value = node
        startXProperty().bindBidirectional(node.centerXProperty())
        startYProperty().bindBidirectional(node.centerYProperty())
    }
    fun Collection<Node>.getItemUnderMouse(evt: MouseEvent): Node? {
        return this.firstOrNull {
            val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
            it.contains(mousePt.x, mousePt.y)
        }
    }
    fun Pane.getMousePosition(evt: MouseEvent) = sceneToLocal(evt.sceneX, evt.sceneY)
    fun getMovingNodeRadius() = movingNode!!.radius

    fun removeStyleFromNodes(styleClass: CssRule) {
    workAreaNodes.forEach {
        if (it.hasClass(styleClass))
            it.removeClass(styleClass)
    }
}
}
