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
import javafx.scene.shape.Line
import tornadofx.*

class WorkingField : Fragment() {

    private val circleRadius = 25.0

    private val toolboxItems = mutableListOf<Node>()
    private val workingFieldItems = mutableListOf<Node>()

    private var movingNode: Node? = null
    private var movingLine: Line? = null

    private var toolbox: Parent by singleAssign()
    private var workArea: Pane by singleAssign()
    private val automataStateEditor = find(AutomataStateEditor::class)

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
            add(automataStateEditor)

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

        toolboxItems.getItemUnderMouse(evt)
                    .apply {
                        if( this != null ) {
                            movingNode = createDefaultStateNode()
                            workArea.add(movingNode!!)
                            movingNode!!.addClass(Styles.movingAutomataState)
                            //It's "kostyl" but works perfect ;)
                            movingNode!!.relocate(-1000.0, -1000.0)
                        }
                    }

        workingFieldItems.getItemUnderMouse(evt)
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

        val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )
        if (workArea.contains(mousePt) && movingNode != null) {
            movingNode!!.relocate( mousePt.x, mousePt.y )
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
            val mousePt = workArea.sceneToLocal( evt.sceneX, evt.sceneY )

            (movingNode!! as StateNode).apply {
                xCoordinateProperty.value = mousePt.x
                yCoordinateProperty.value = mousePt.y
                connections.forEach {
                    it.apply {
                        when(this) {
                            it.startNodeProperty.value -> {
                                startX = mousePt.x
                                startY = mousePt.y
                            }
                            it.endNodeProperty.value -> {
                                endX = mousePt.x
                                endY = mousePt.y
                            }
                        }
                    }
                }
            }

            movingNode!!.removeClass(Styles.movingAutomataState)
            workingFieldItems.add(movingNode!!)
        }
        else {
            val node = workingFieldItems.getItemUnderMouse(evt)
            if (movingLine != null) {
                if (node != null) {
                    node as StateNode
                    (movingLine as Connection).apply {
                        endX = node.xCoordinateProperty.value
                        endY = node.yCoordinateProperty.value
                        endNodeProperty.value = node
                    }
                }
                else {
                    movingLine!!.removeFromParent()
                }
            }
        }

        movingLine = null
        movingNode = null
    }

    private fun selectNode(selectedNode: StateNode) {
        automataStateEditor.stateModel.rebind { node = selectedNode }
        removeStyleFromNodes(Styles.chosenAutomataState)
        selectedNode.addClass(Styles.chosenAutomataState)
    }
    private fun startConnection(node: StateNode) {
        movingLine = Connection().apply {
            startNodeProperty.value = node
            startX = node.xCoordinateProperty.value
            startY = node.yCoordinateProperty.value
        }
        workArea.add(movingLine!!)
    }
    private fun startDragging(node: Node) {
        movingNode = node
        movingNode!!.addClass(Styles.movingAutomataState)
        workingFieldItems.remove(node)
    }

    private fun Collection<Node>.getItemUnderMouse(evt: MouseEvent): Node? {
        return this.firstOrNull {
            val mousePt = it.sceneToLocal(evt.sceneX, evt.sceneY)
            it.contains(mousePt.x, mousePt.y)
        }
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
