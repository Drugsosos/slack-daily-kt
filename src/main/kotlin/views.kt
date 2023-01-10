import com.slack.api.bolt.App

/**
 * Class w/ app instance and method to add all listeners to the app
 */
class Listeners {
    val app = App()

    /**
     * Adds all listeners to the app
     */
    fun addListeners() {
        //TODO: WIP
        app.command("/channel_append") { req, ctx ->
            logger.warn {
                "/channel_append: Channel: ${ctx.channelId}\tUser: ${ctx.requestUserId}"
            }

            if (isDMMessage(ctx.client(), req.payload.channelName, ctx.requestUserId)) { ctx.ack() }
            else {
                ctx.say("test")
                ctx.ack()
            }
        }
    }
}
