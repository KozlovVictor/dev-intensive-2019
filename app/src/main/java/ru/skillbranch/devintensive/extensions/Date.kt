package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, timeUnit: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (timeUnit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val deference = this.time - date.time
    val future = "через"
    val past = "назад"
    val isPast = deference < 0
    return when (val absDeference = abs(deference)) {
        in 0 until 1 * SECOND -> "только что"
        in 1 * SECOND until 45 * SECOND -> if (isPast) "несколько секунд $past" else "$future несколько секунд"
        in 45 * SECOND until 75 * SECOND -> if (isPast) "минуту $past" else "$future минуту"
        in 75 * SECOND until 45 * MINUTE ->
            if (isPast) {
                "${TimeUnits.MINUTE.plural((absDeference / MINUTE).toInt())} $past"
            } else {
                "$future ${TimeUnits.MINUTE.plural((absDeference / MINUTE).toInt())}"
            }
        in 45 * MINUTE until 75 * MINUTE -> if (isPast) "час $past" else "$future час"
        in 75 * MINUTE until 22 * HOUR -> if (isPast) {
            "${TimeUnits.HOUR.plural((absDeference / HOUR).toInt())} $past"
        } else {
            "$future ${TimeUnits.HOUR.plural((absDeference / HOUR).toInt())}"
        }
        in 22 * HOUR until 26 * HOUR -> if (isPast) "день $past" else "$future день"
        in 26 * HOUR until 360 * DAY -> if (isPast) {
            "${TimeUnits.DAY.plural((absDeference / DAY).toInt())} $past"
        } else {
            "$future ${TimeUnits.DAY.plural((absDeference / DAY).toInt())}"
        }
        else -> if (isPast) "более года назад" else "более чем через год"
    }
}

enum class TimeUnits {
    SECOND {
        override fun plural(value: Int): String {
            if (value == 11) return "$value секунд"
            return when (value % 10) {
                1 -> "$value секунду"
                2, 3, 4 -> "$value секунды"
                else -> "$value секунд"
            }
        }
    },
    MINUTE {
        override fun plural(value: Int): String {
            if (value == 11) return "$value минут"
            return when (value % 10) {
                1 -> "$value минуту"
                2, 3, 4 -> "$value минуты"
                else -> "$value минут"
            }
        }
    },
    HOUR {
        override fun plural(value: Int): String {
            if (value == 11) return "$value часов"
            return when (value % 10) {
                1 -> "$value час"
                2, 3, 4 -> "$value часа"
                else -> "$value часов"
            }
        }
    },
    DAY {
        override fun plural(value: Int): String {
            if (value == 11) return "$value дней"
            return when (value % 10) {
                1 -> "$value день"
                2, 3, 4 -> "$value дня"
                else -> "$value дней"
            }
        }
    };

    abstract fun plural(value: Int): String
}