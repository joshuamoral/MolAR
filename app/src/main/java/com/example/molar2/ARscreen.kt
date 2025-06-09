package com.example.molar2

import android.view.MotionEvent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.ar.core.Config
import com.google.ar.core.Frame
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.ar.rememberARCameraNode
import io.github.sceneview.model.ModelInstance
import io.github.sceneview.node.Node
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener
import io.github.sceneview.rememberView
import io.github.sceneview.ar.node.AnchorNode
import io.github.sceneview.node.ModelNode

@Composable
fun ARScreen(navController: NavController, model: String) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine = engine)
    val materialLoader = rememberMaterialLoader(engine = engine)
    val cameraNode = rememberARCameraNode(engine = engine)
    val childNodes = rememberNodes()
    val view = rememberView(engine = engine)
    val collisionSystem = rememberCollisionSystem(view = view)

    val planeRenderer = remember { mutableStateOf(true) }
    val modelInstance = remember { mutableListOf<ModelInstance>() }
    val frame = remember { mutableStateOf<Frame?>(null) }

    ARScene(
        modifier = Modifier.fillMaxSize(),
        engine = engine,
        view = view,
        childNodes = childNodes,
        modelLoader = modelLoader,
        cameraNode = cameraNode,
        collisionSystem = collisionSystem,
        planeRenderer = planeRenderer.value,
        materialLoader = materialLoader,
        onSessionUpdated = { _, updatedFrame ->
            frame.value = updatedFrame
        },
        sessionConfiguration = { session, config ->
            config.depthMode = if (session.isDepthModeSupported(Config.DepthMode.AUTOMATIC)) {
                Config.DepthMode.AUTOMATIC
            } else {
                Config.DepthMode.DISABLED
            }
            config.lightEstimationMode = Config.LightEstimationMode.ENVIRONMENTAL_HDR
        },
        onGestureListener = rememberOnGestureListener(
            onSingleTapConfirmed = { e: MotionEvent, node: Node? ->
                if (node == null) {
                    val hitResult = frame.value?.hitTest(e.x, e.y)?.firstOrNull {
                        it.isValid(depthPoint = false, point = false)
                    }
                    val anchor = hitResult?.createAnchorOrNull()
                    anchor?.let {
                        val modelNode = ModelNode(
                            modelInstance = modelInstance.apply {
                                if (isEmpty()) {
                                    this += modelLoader.createInstancedModel(model, 10)
                                }
                            }.last(),
                            scaleToUnits = 0.2f
                        )
                        val anchorNode = AnchorNode(engine, it)
                        anchorNode.addChildNode(modelNode)
                        childNodes += anchorNode
                    }
                }
            }
        )
    )
}
