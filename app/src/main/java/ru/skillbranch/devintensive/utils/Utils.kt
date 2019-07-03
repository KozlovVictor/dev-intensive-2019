package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName: String?) : Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")
        val firstName = if (parts?.getOrNull(0).isNullOrEmpty()) null else parts?.getOrNull(0)
        val lastName = if (parts?.getOrNull(1).isNullOrEmpty()) null else parts?.getOrNull(1)
        return firstName to lastName
    }
}