package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.*
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

    @Test
    fun test_builder() {
        val victor = User.Builder()
            .id("15")
            .firstName("Victor")
            .lastName("Kozlov")
            .avatar("url")
            .rating(15)
            .respect(50)
            .lastVisit(Date().add(-20, TimeUnits.MINUTE))
            .isOnline(false)
            .build()
        victor.printMe()
    }

    @Test
    fun test_plural() {
        println(
            "${TimeUnits.SECOND.plural(1)} \n" + //1 секунду
                    "${TimeUnits.MINUTE.plural(4)} \n" + //4 минуты
                    "${TimeUnits.HOUR.plural(19)} \n" +//19 часов
                    TimeUnits.DAY.plural(222)//222 дня
        )
    }

    @Test
    fun test_truncate() {
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate()) //Bender Bending R...
        println("Bender Bending Rodriguez — дословно «Сгибальщик Сгибающий Родригес»".truncate(15)) //Bender Bending...
        println("A     ".truncate(3)) //A
    }

    @Test
    fun test_stripHtml() {
//        println("&ldfmb; &иклиока;".stripHtml())
        println("&игра; amp lt &gt 39; meters ()quot;".stripHtml())
//        println('А'.toByte().toInt())
//        println('Б'.toByte().toInt())
//        println('Ю'.toByte().toInt())
//        println('Я'.toByte().toInt())
//        println("--------------------")
//        println('а'.toByte().toInt())
//        println('б'.toByte().toInt())
//        println('я'.toByte().toInt())

        assertEquals(
            "Образовательное IT-сообщество Skill Branch",
            "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml()
        )
        assertEquals(
            "Образовательное IT-сообщество Skill Branch",
            "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml()
        )

        /* additional tests */
        assertEquals("single", "&amp;&lt;&gt;single&#39;&quot;".stripHtml())
        assertEquals("", "&amp;&lt;&gt;&#39;&quot;".stripHtml())
        assertEquals(" ", "&amp;&lt;&gt;    &#39;&quot;".stripHtml())
        assertEquals("1978", "<path fill=\"Color\" d=\"M11.63 10z\"></svg><span>1978</span>".stripHtml())
        assertEquals("", "&gt;<head>&#39;&quot;</head>".stripHtml())
        assertEquals(" ", "&gt;<head> &quot; </head>".stripHtml())
        assertEquals("&игра; amp lt &gt 39; meters ()quot;", "&игра; amp lt &gt 39; meters ()quot;".stripHtml())
        assertEquals(" one two ", "  one   two ".stripHtml())
        assertEquals("null", "null".stripHtml())
        val longHtml = """
            <TD valign="top" style="padding-bottom:15px;"> <b>line1<b> </TD>
            <TD valign="top"> <span class="HeadTitleNews"> line2</span>
            <img src='http://2011WaterpoloF.jpg' >
            <div style="margin: 0in 0in 0pt">line3</div>
        """.trimIndent()
        println(longHtml.stripHtml())
        assertEquals(" line1 \n line2\n\nline3", longHtml.stripHtml())
    }
}
