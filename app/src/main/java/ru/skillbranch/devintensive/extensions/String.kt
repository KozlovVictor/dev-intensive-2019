package ru.skillbranch.devintensive.extensions

fun String.truncate(count: Int = 16): String {
    val tempString = this.substring(0, count)
    return "${if (tempString.last().toString() == " ") tempString.substring(
        0,
        tempString.length - 2
    ) else tempString}..."
}

fun String.stripHtml(): String {
//    return this.replace("/^[<]{1}(?![А-я]$){.*}{2}[>]$/", "")
//    val regex = Regex("""^(?![А-я]$)[&<].*[;>]$""")
    fun removeSpaces(target: String): String {
        var spaceCount = 0
        val result = StringBuilder()
        this.forEach { char ->
            if (char.toString() != " ") {
                spaceCount = 0
                result.append(char.toString())
            } else {
                if (spaceCount == 0) {
                    spaceCount++
                    result.append(char.toString())
                }
            }
        }
        return result.toString()
    }

//    val regex = Regex("""[<&][^А-я][^ ].*?[;>]""")
//    val regex = Regex("""<[^>]*>""")
//    return regex.replace(this, "")
    return this.replace("(<.*?>)|(&quot;)|(&amp;)|(&gt;)|(&lt;)|(&#39;)".toRegex(), "").replace("\\s+".toRegex(), " ")
//        .replace("<.*?>".toRegex(), "")
//        .replace("""[<&][^А-я].*?[;>]""".toRegex(), "")
//        .replace("\\s+".toRegex()," ")

}