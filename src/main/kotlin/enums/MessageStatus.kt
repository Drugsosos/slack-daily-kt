package enums

/**
 * All statuses of informational messages
 *
 * @constructor asEmoji Emoji representation of status
 */
enum class MessageStatus(val asEmoji: String) {
    Success(":white_check_mark:"),
    Error(":x:");
}
