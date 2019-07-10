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
//    val regex = Regex("""[<&][^А-я][^ ].*?[;>]""")
//    val regex = Regex("""<[^>]*>""")
//    return regex.replace(this, "")
    return this
        .replace("<.*?>".toRegex(), "")
        .replace("\\s+".toRegex(), " ")
        .replace("['&]".toRegex(), "")
//        replace("(<.*?>)|(&quot;)|(&amp;)|(&gt;)|(&lt;)|(&#39;)".toRegex(), "").replace("\\s+".toRegex(), " ")
//        .replace("<.*?>".toRegex(), "")
//        .replace("""[<&][^А-я].*?[;>]""".toRegex(), "")
//        .replace("\\s+".toRegex()," ")
}