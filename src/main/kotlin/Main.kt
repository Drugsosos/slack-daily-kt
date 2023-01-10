import com.slack.api.bolt.socket_mode.SocketModeApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration
import mu.KotlinLogging


val logger = KotlinLogging.logger {}

suspend fun launchBot() {
    val applicationInstance = Listeners()
    applicationInstance.addListeners()

    val socketModeApp = SocketModeApp(applicationInstance.app)
    socketModeApp.start()

    delay(Duration.INFINITE)
}


fun main(): Unit = runBlocking {
    launch {
        launchBot()
    }
}
