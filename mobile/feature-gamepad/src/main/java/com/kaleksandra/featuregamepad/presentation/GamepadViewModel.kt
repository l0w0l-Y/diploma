package com.kaleksandra.featuregamepad.presentation

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaleksandra.corecommon.ext.log.debug
import com.kaleksandra.coredata.network.config.DataConfig.IP
import com.kaleksandra.coredata.network.config.DataConfig.TOKEN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import java.io.ByteArrayInputStream
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.fixedRateTimer

@HiltViewModel
class GamepadViewModel @Inject constructor() : ViewModel() {

    private var _imageState = MutableSharedFlow<ImageBitmap>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    val imageState: SharedFlow<ImageBitmap> get() = _imageState

    private val _frameState = MutableStateFlow<Int>(0)
    val frameState: StateFlow<Int> get() = _frameState

    private val _pingState = MutableStateFlow<Long>(0L)
    val pingState: StateFlow<Long> get() = _pingState

    private val _callCount = MutableStateFlow<Int>(0)

    private var timer: Timer? = null

    private var webSocketClient: WebSocket? = null

    init {
        fixedRateTimer("Counter", true, 0, 1000) {
            viewModelScope.launch {
                _frameState.emit(_callCount.value)
                _callCount.emit(0)
            }
        }
        fixedRateTimer("Ping", true, 0, 1000) {
            val startTime = System.currentTimeMillis()
            val isResult = webSocketClient?.send("")
            if (isResult == true) {
                viewModelScope.launch {
                    val endTime = System.currentTimeMillis()
                    val pingTime = endTime - startTime
                    _pingState.emit(pingTime)
                }
            }
        }
        onSocketConnect()
    }

    fun sendAction(action: String) {
        webSocketClient?.send(action)
    }

    suspend fun updateValue(newValue: ImageBitmap) {
        _imageState.emit(newValue)
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
        timer = null
    }

    //TODO: Move to core-data
    private fun connectToWebSocket() {
        val client = OkHttpClient()
        val request =
            Request.Builder().url("${IP}imageSocket?token=${TOKEN}=true")
                .build()
        val listener = object : WebSocketListener() {
            override fun onOpen(webSocket: WebSocket, response: Response) {
                debug("WebSocket connection opened")
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                super.onMessage(webSocket, text)
                println(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: okio.ByteString) {
                try {
                    viewModelScope.launch {
                        val byteArray = bytes.toByteArray()
                        val bitmap = BitmapFactory.decodeStream(ByteArrayInputStream(byteArray))
                        _callCount.update { it + 1 }
                        updateValue(bitmap.asImageBitmap())
                    }
                } catch (e: Exception) {
                    error(e.message.toString())
                }
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                debug("WebSocket connection closed: $reason")
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                debug("WebSocket connection failed", t.toString())
            }
        }
        client.newWebSocket(request, listener)
        val messageRequest =
            Request.Builder().url("${IP}movement?token=${TOKEN}=true")
                .build()
        webSocketClient = client.newWebSocket(messageRequest, listener)
    }

    private fun onSocketConnect() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                connectToWebSocket()
            }
        }
    }
}
