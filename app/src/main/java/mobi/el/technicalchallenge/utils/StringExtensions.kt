package mobi.el.technicalchallenge.utils

import java.util.regex.Pattern

private val EMAIL_ADDRESS_PATTERN = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun String.isEmailValid(): Boolean {
    return !this.isNullOrEmpty() && EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}

fun String.isPasswordValid(): Boolean {
    return !this.isNullOrEmpty() && this.length >= 6
}

fun String.isAgeValid(): Boolean {
    return !this.isNullOrEmpty() && this.toInt() >= 18 && this.toInt() <= 99
}
