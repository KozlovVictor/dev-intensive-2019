package ru.skillbranch.devintensive.utils

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
}