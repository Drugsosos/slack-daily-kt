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
