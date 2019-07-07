package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.humanizeDiff
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.utils.Utils
import java.util.*

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

    @Test
    fun test_toInitials() {
        println(
            "${Utils.toInitials("john", "doe")} \n" +
                    "${Utils.toInitials(null, "doe")} \n" +
                    "${Utils.toInitials("John", null)} \n" +
                    "${Utils.toInitials(null, null)} \n" +
                    "${Utils.toInitials(" ", "")}"
        )
    }

    @Test
    fun test_transliteration() {
        println(
            "${Utils.transliteration("Придурок вбеЖал на камод!", "%")} \n" +
                    "${Utils.transliteration("Nikolay_kurenov", "_")}\n" +
                    "${Utils.transliteration("Женя Стереотипов")}\n" +
                    "${Utils.transliteration("Amazing Петр", "_")}"
        )
    }

    @Test
    fun test_humanizeDiff() {
        val users: MutableList<User> = mutableListOf()
        val user1 = User.makeUser("gool vool")
        users.add(user1)
        users.add(user1.copy(lastVisit = Date().add(-30, TimeUnits.SECOND)))
        users.add(user1.copy(lastVisit = Date().add(-63, TimeUnits.SECOND)))
        users.add(user1.copy(lastVisit = Date().add(-31, TimeUnits.MINUTE)))
        users.add(user1.copy(lastVisit = Date().add(-57, TimeUnits.MINUTE)))
        users.add(user1.copy(lastVisit = Date().add(-16, TimeUnits.HOUR)))
        users.add(user1.copy(lastVisit = Date().add(-25, TimeUnits.HOUR)))
        users.add(user1.copy(lastVisit = Date().add(-25, TimeUnits.DAY)))
        users.add(user1.copy(lastVisit = Date().add(-400, TimeUnits.DAY)))
        users.add(user1.copy(lastVisit = Date().add(30, TimeUnits.SECOND)))
        users.add(user1.copy(lastVisit = Date().add(63, TimeUnits.SECOND)))
        users.add(user1.copy(lastVisit = Date().add(31, TimeUnits.MINUTE)))
        users.add(user1.copy(lastVisit = Date().add(57, TimeUnits.MINUTE)))
        users.add(user1.copy(lastVisit = Date().add(16, TimeUnits.HOUR)))
        users.add(user1.copy(lastVisit = Date().add(25, TimeUnits.HOUR)))
        users.add(user1.copy(lastVisit = Date().add(25, TimeUnits.DAY)))
        users.add(user1.copy(lastVisit = Date().add(400, TimeUnits.DAY)))
        users.add(user1.copy(lastVisit = Date().add(-11, TimeUnits.SECOND)))
        users.add(user1.copy(lastVisit = Date().add(-11, TimeUnits.HOUR)))
        users.add(user1.copy(lastVisit = Date().add(-11, TimeUnits.DAY)))
        users.add(user1.copy(lastVisit = Date().add(11, TimeUnits.MINUTE)))

        val user2 = (user1.copy(lastVisit = Date().add(-12000, TimeUnits.MINUTE)))

        users.forEach { user ->
            println(
                "${user.lastVisit?.humanizeDiff()}"
            )
        }
        println("------------------------------------------------------\n")
        println(user2.lastVisit?.humanizeDiff())
    }
}
