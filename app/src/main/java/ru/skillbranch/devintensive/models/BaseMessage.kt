package ru.skillbranch.devintensive.models

import java.util.Date

abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {
    abstract fun formatMessage()

    companion object AbstractFactory {
        fun makeMessage(
            from: User?,
            chat: Chat,
            date: Date,
            type: String,
            payload: String,
            isIncoming: Boolean = false
        ) {
            TODO("not implemented")
        }
    }
}