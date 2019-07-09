package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    val tempString = this.substring(0, count)
    return "${if (tempString.last().toString() == " ") tempString.substring(
        0,
        tempString.length - 2
    ) else tempString}..."
}

fun String.stripHtml(): String {
    var isTag = false
    var isEscape = false
    var spaceCount = 0
    val startTag = "<"
    val endTag = ">"
    val startEscape = "&"
    val endEscape = ";"
    val result = StringBuilder()
    this.forEach { char ->
        if (!isTag) {
            if (!isEscape) {
                if (char.toString() != startTag) {
                    if (char.toString() != startEscape) {
                        if (char.toString() != " ") {
                            if (char.toByte().toInt() !in 16..79) {
                                spaceCount = 0
                                result.append(char.toString())
                            }
                        } else {
                            if (spaceCount < 1) {
                                spaceCount++
                                result.append(char.toString())
                            } else {
                                spaceCount++
                            }
                        }
                    } else {
                        isEscape = true
                    }
                } else {
                    isTag = true
                }
            } else {
                if (char.toString() == endEscape) isEscape = false
            }
        } else {
            if (char.toString() == endTag) isTag = false
        }
    }
    return result.toString()
}