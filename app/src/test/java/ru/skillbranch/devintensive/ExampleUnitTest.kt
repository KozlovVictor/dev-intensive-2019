package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import java.util.Date

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
    fun test_create_user() {
        val user = User.makeUser("Kozlov Victor")
        user.printMe()
    }

    @Test
    fun test_parse_fullname() {
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

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Kozlov Victor")
        val textMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imageMessage = BaseMessage.makeMessage(user, Chat("0"), payload = "any image url", type = "image")

        println(textMessage.formatMessage())
        println(imageMessage.formatMessage())
    }

    @Test
    fun test_data() {
        val user1 = User.makeUser("gool vool")
        val user2 = user1.copy(lastVisit = Date().add(-2, TimeUnits.MINUTE))
        val user3 = user1.copy(lastVisit = Date().add(4, TimeUnits.DAY))

        println(
            """
            ${user1.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user2.lastVisit?.format("yyyy.MM.dd G 'at' HH:mm:ss z")}
            ${user3.lastVisit?.format("EEE, MMM d, ''yy")}
        """.trimIndent()
        )
    }
}
