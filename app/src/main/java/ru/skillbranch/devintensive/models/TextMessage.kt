package ru.skillbranch.devintensive.models

import java.util.Date

class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean,
    date: Date

) : BaseMessage(id, from, chat, isIncoming, date) {
    override fun formatMessage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}