package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.models.User

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun create_user() {
        val user = User.makeUser("Kozlov Victor")
        user.printMe()
    }
    @Test
    fun parse_fullname() {
        val user1 = User.makeUser("gool vool")
        val user2 = User.makeUser(null)
        val user3 = User.makeUser("")
        val user4 = User.makeUser(" ")
        val user5 = User.makeUser("John")
        val user6 = User.makeUser(" Watson")
        println(user1)
        println(user2)
        println(user3)
        println(user4)
        println(user5)
        println(user6)
    }
}
