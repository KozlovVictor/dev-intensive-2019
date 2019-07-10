package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    val tempString = this.substring(0, count)
    return "${if (tempString.last().toString() == " ") tempString.substring(
        0,
        tempString.length - 2
    ) else tempString}..."
}

fun String.stripHtml(): String {
    return this
        .replace("<.*?>".toRegex(), "")
        .replace("\\s+".toRegex(), " ")
        .replace("['&]".toRegex(), "")
}