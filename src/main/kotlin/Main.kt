import com.slack.api.bolt.App
import com.slack.api.bolt.AppConfig
import com.slack.api.bolt.socket_mode.SocketModeApp
import com.slack.api.model.Message.MessageItem
import com.slack.api.model.block.Blocks.asBlocks
import com.slack.api.model.block.Blocks.section
import com.slack.api.model.block.composition.BlockCompositions.markdownText


fun main() {
    val appConfig = AppConfig.builder()
        .clientId("401593055731.4102576073862")
        .clientSecret("41d2d1f36a8e17e479c319830183829a")
//        .scope("app_mentions:read,chat:write,commands")
        .oauthInstallPath("install")
        .oauthRedirectUriPath("oauth_redirect")
        .build()

    // Initialize the App and register listeners
    val app = App(appConfig)

//    app.event(AppMentionEvent::class.java) { event, ctx ->
//        ctx.say("<@${event.event.user}> What's up?")
//        ctx.ack()
//    }

    app.command("/test") { req, ctx ->
        // requires channels:join scope
        ctx.client().conversationsJoin { it.channel(req.payload.channelId) }
        ctx.say(asBlocks(
            section { it.blockId("foo").text(markdownText("This is foo")) }
        ))
        ctx.ack()
    }



    val appToken = "xapp-1-A0430GY25RC-4121829885025-725cb074d23baa20ecce12e45ea6a3337b1ffd2c834fc8c367f379044b2248e8"
    val socketModeApp = SocketModeApp(appToken, app)

    socketModeApp.startAsync()
}
