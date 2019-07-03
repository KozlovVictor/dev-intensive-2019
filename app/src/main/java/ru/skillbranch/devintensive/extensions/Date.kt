package ru.skillbranch.devintensive.extensions

import java.util.Date

fun Date.format(pattern: String) {
    TODO("not implemented")
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