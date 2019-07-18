package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val focusedView = currentFocus
    if (focusedView != null) {
        (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            focusedView.windowToken,
            0
        )
    }
}

