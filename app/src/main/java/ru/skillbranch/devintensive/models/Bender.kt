package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    private var wrongAnswerCount = 0

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun listenAnswer(answer: String): Pair<String, Triple<Int, Int, Int>> {
        return if (!validation(answer)) {
            "${question.validateError()}\n${question.question}" to status.color
        } else {
            if (question.answers.contains(answer.toLowerCase())) {
                question = question.nextQuestion()
                "Отлично - ты справился\n${question.question}" to status.color
            } else {
                if (question == Question.IDLE) return question.question to status.color
                wrongAnswerCount++
                if (wrongAnswerCount < 3) {
                    status = status.nextStatus()
                    "Это неправильный ответ\n${question.question}" to status.color
                } else {
                    status = Status.NORMAL
                    question = Question.NAME
                    "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                }
            }
        }
    }

    private fun validation(answer: String): Boolean {
        return when (question) {
            Question.NAME -> !answer.isBlank() && answer[0].isUpperCase()
            Question.PROFESSION -> !answer.isBlank() && answer[0].isLowerCase()
            Question.MATERIAL -> {
                val regex = """\d""".toRegex()
                !regex.containsMatchIn(answer)
            }
            Question.BDAY -> {
                val regex = """^\d+$""".toRegex()
                regex.matches(answer)
            }
            Question.SERIAL -> {
                val regex = """^[\d+]{7}$""".toRegex()
                regex.containsMatchIn(answer)
            }
            Question.IDLE -> true
        }
    }

    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 0, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("bender", "бендер")) {
            override fun validateError(): String = "Имя должно начинаться с заглавной буквы"
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("bender", "сгибальщик")) {
            override fun validateError(): String = "Профессия должна начинаться со строчной буквы"
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("металл", "дерево", "metal", "iron", "wood")) {
            override fun validateError(): String = "Материал не должен содержать цифр"
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("2993")) {
            override fun validateError(): String = "Год моего рождения должен содержать только цифры"
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057")) {
            override fun validateError(): String = "Серийный номер содержит только цифры, и их 7"
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()) {
            override fun validateError(): String = ""
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
        abstract fun validateError(): String
    }
}