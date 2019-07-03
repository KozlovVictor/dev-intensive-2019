package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(dateFormat)
}

fun Date.add(value: Int, timeUnit: TimeUnits) {
    TODO("not implemented")
}

//FIXME
fun Date.humanizeDiff(): String {
    return this.toString()
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}