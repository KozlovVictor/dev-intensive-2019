package ru.skillbranch.devintensive.utils

import android.content.Context

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) null else parts?.getOrNull(1)
        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstCharacter = firstName?.getOrNull(0)?.toString()?.toUpperCase()
        val lastCharacter = lastName?.getOrNull(0)?.toString()?.toUpperCase()
        return when {
            firstCharacter.isNullOrBlank() && lastCharacter.isNullOrBlank() -> null
            firstCharacter.isNullOrBlank() -> lastCharacter
            lastCharacter.isNullOrBlank() -> firstCharacter
            else -> "$firstCharacter$lastCharacter"
        }
    }

    fun transliteration(payload: String?, divider: String = " "): String? {
        val vocabulary: HashMap<String, String> = hashMapOf(
            "а" to "a",
            "б" to "b",
            "в" to "v",
            "г" to "g",
            "д" to "d",
            "е" to "e",
            "ё" to "e",
            "ж" to "zh",
            "з" to "z",
            "и" to "i",
            "й" to "i",
            "к" to "k",
            "л" to "l",
            "м" to "m",
            "н" to "n",
            "о" to "o",
            "п" to "p",
            "р" to "r",
            "с" to "s",
            "т" to "t",
            "у" to "u",
            "ф" to "f",
            "х" to "h",
            "ц" to "c",
            "ч" to "ch",
            "ш" to "sh",
            "щ" to "sh'",
            "ъ" to "",
            "ы" to "i",
            "ь" to "",
            "э" to "e",
            "ю" to "yu",
            "я" to "ya",
            " " to divider
        )
        val result = StringBuilder()
        payload?.let {
            it.forEach { char ->
                val value = if (char.isUpperCase()) {
                    val temp = vocabulary[char.toString().toLowerCase()]?.toUpperCase()
                    if (temp?.length == 2) {
                        "${temp[0]}" + "${temp[1].toLowerCase()}"
                    } else {
                        temp
                    }
                } else {
                    vocabulary[char.toString()]
                }
                result.append(
                    if (value.isNullOrEmpty()) char else value
                )
            }
        }
        return result.toString()
    }

    fun verification(github: String) =
        Regex("((https://|www.|https://www.)?github.com/(?!enterprise$|features$|topics$|collections$|trending$|events$|marketplace$|pricing$|nonprofit$|customer-stories$|security$|login$|join$)[\\w\\d-_]{1,39}/?$)|").find(
            github
        )?.value == github

    fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density + 0.5f).toInt()
    }

    fun pxToDp(context: Context, px: Int): Int {
        return (px / context.resources.displayMetrics.density + 0.5f).toInt()
    }
}