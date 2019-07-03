package ru.skillbranch.devintensive.models

import java.util.*

class User(
    val id: String,
    val firstName: String?,
    val lastName: String?,
    val avatar: String?,
    val rating: Int = 0,
    val respect: Int = 0,
    val lastVisit: Date? = Date(),
    val isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe $id")

    init {
        println("It's Alive!!!")
    }

    fun printMe() = println(
        """
        id: $id
        firstName: $firstName
        lastName: $lastName
        avatar: $avatar
        rating: $rating
        respect: $respect
        lastVisit: $lastVisit
        isOnline: $isOnline
        """.trimIndent()
    )

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User {
            lastId++
            val parts: List<String>? = fullName?.split(" ")
            val firstName = parts?.getOrNull(0)
            val lastName = parts?.getOrNull(1)
            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }
}