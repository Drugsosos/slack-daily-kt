import com.slack.api.methods.MethodsClient
import com.slack.api.methods.request.chat.ChatPostMessageRequest
import com.slack.api.methods.request.conversations.ConversationsOpenRequest
import enums.MessageStatus

/**
 * Parses Int to Slack emoji representation
 *
 * @param number Number to be represented as a String
 */
fun intToSlackEmoji (number: Int): String {
    val englishNumbers = listOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")

    var remaining = number
    val result = mutableListOf<String>()

    while (remaining > 0) {
        result.add(englishNumbers[remaining % 10])
        remaining /= 10
    }

    return result.reversed().joinToString { ":${it}:" }
}


/**
 * Checks if command was used in DM
 *
 * @param slackClient Instance of Slack API Methods client.
 * @param channelName Slack channel name
 * @param userID Slack user id
 * @return True if command was used in DM & sends warning back to DM
 */
fun isDMMessage(
    slackClient: MethodsClient,
    channelName: String,
    userID: String
): Boolean {
    if (channelName != "directmessage") {
        return false
    }

    val dmID = slackClient.conversationsOpen { ConversationsOpenRequest.builder().users(listOf(userID)) }.channel.id

    slackClient.chatPostMessage {
        ChatPostMessageRequest
            .builder()
            .channel(dmID)
            .text(":x: You can't use commands in DMs")
            .blocks(
                Blocks().statusMessage(
                    MessageStatus.Error,
                    "You can't use commands in DMs",
                    "Open any available channel and send the command there"
                )
            )
    }

    logger.info { "User $userID sent command in DM" }
    return true
}
