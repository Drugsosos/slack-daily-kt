fun intToSlackEmoji (number: Int): String {
    val englishNumbers = arrayOf("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")

    var remaining = number
    val result = mutableListOf<String>()

    while (remaining > 0) {
        result.add(englishNumbers[remaining % 10])
        remaining /= 10
    }

    return result.reversed().joinToString { ":${it}:" }
}
