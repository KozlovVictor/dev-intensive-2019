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
                "${absDeference / MINUTE} ${numeral(absDeference / MINUTE, TimeUnits.MINUTE)} $past"
            } else {
                "$future ${deference / MINUTE} ${numeral(absDeference / MINUTE, TimeUnits.MINUTE)}"
            }
        in 45 * MINUTE until 75 * MINUTE -> if (isPast) "час $past" else "$future час"
        in 75 * MINUTE until 22 * HOUR -> if (isPast) {
            "${absDeference / HOUR} ${numeral(absDeference / HOUR, TimeUnits.HOUR)} $past"
        } else {
            "$future ${absDeference / HOUR} ${numeral(absDeference / HOUR, TimeUnits.HOUR)}"
        }
        in 22 * HOUR until 26 * HOUR -> if (isPast) "день $past" else "$future день"
        in 26 * HOUR until 360 * DAY -> if (isPast) {
            "${absDeference / DAY} ${numeral(absDeference / DAY, TimeUnits.DAY)} $past"
        } else {
            "$future ${absDeference / DAY} ${numeral(absDeference / DAY, TimeUnits.DAY)}"
        }
        else -> if (isPast) "более года назад" else "более чем через год"
    }
}

private fun numeral(value: Long, timeUnit: TimeUnits): String {
    if (value.toInt() == 11) {
        return when (timeUnit) {
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
    } else {
        when (value.toInt() % 10) {
            1 -> return when (timeUnit) {
                TimeUnits.SECOND -> "секунду"
                TimeUnits.MINUTE -> "минуту"
                TimeUnits.HOUR -> "час"
                TimeUnits.DAY -> "день"
            }
            2, 3, 4 -> return when (timeUnit) {
                TimeUnits.SECOND -> "секунды"
                TimeUnits.MINUTE -> "минуты"
                TimeUnits.HOUR -> "часа"
                TimeUnits.DAY -> "дня"
            }
            else -> return when (timeUnit) {
                TimeUnits.SECOND -> "секунд"
                TimeUnits.MINUTE -> "минут"
                TimeUnits.HOUR -> "часов"
                TimeUnits.DAY -> "дней"
            }
        }
    }
}

enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY
}