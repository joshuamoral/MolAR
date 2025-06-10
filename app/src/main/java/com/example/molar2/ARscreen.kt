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
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


import io.github.sceneview.ar.ARScene





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

    // ✅ Safe cleanup with DisposableEffect
//    DisposableEffect(Unit) {
//        onDispose {
//            Log.d("ARCleanup", "Cleaning up ${childNodes.size} nodes...")
//
//            try {
//                childNodes.forEach { node ->
//                    try {
//                        if (node is AnchorNode) {
//                            node.detachAnchor()   // ✅ safe
//                        }
//                        node.destroy()           // ✅ safe
//                    } catch (e: Exception) {
//                        Log.e("ARCleanup", "Error cleaning node", e)
//                    }
//                }
//            } catch (e: Exception) {
//                Log.e("ARCleanup", "Error cleaning nodes", e)
//            }
//
//            try {
//                engine.destroy()
//                Log.d("ARCleanup", "Engine destroyed safely")
//            } catch (e: Exception) {
//                Log.e("ARCleanup", "Engine destroy failed", e)
//            }
//        }
//    }

    // 👇 Compose function, NOT assignable to a variable
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
                        val newInstance = modelLoader.createInstancedModel(model, 10).last()
                        modelInstance += newInstance

                        val modelNode = ModelNode(
                            modelInstance = newInstance,
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
