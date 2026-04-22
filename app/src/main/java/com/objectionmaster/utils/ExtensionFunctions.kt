package com.objectionmaster.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * Show a Toast message
 */
fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

/**
 * Extension function to safely observe LiveData with nullability handling
 */
fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (T) -> Unit) {
    this.observe(owner) { value ->
        value?.let { observer(it) }
    }
}

/**
 * Extension function to show/hide views
 */
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

/**
 * Extension function for safe text access
 */
fun android.widget.EditText.getTrimmedText(): String {
    return this.text.toString().trim()
}

/**
 * Check if string is empty or blank
 */
fun String?.isEmptyOrBlank(): Boolean {
    return this == null || this.isBlank()
}

/**
 * Capitalize first letter
 */
fun String.capitalizeFirstLetter(): String {
    return if (this.isNotEmpty()) {
        this[0].uppercase() + this.substring(1)
    } else {
        this
    }
}
