@file:JvmName("PasswordUtils")
package dev.retrotv.data.utils

@JvmOverloads
fun checkLength(password: CharSequence, minLength: Int = 8, maxLength: Int = 16): Boolean {
    return password.length in minLength..maxLength
}

@JvmOverloads
fun isInclude(
    password: CharSequence,
    includeLowerCaseEnglish: Boolean = true,
    includeUpperCaseEnglish: Boolean = true,
    includeNumber: Boolean = true,
    includeSpecialCharacter: Boolean = true
): Boolean {
    if (includeLowerCaseEnglish && !isIncludeLowerCase(password.toString())) {
        return false
    }

    if (includeUpperCaseEnglish && !isIncludeUpperCase(password.toString())) {
        return false
    }

    if (includeNumber && !isIncludeNumber(password.toString())) {
        return false
    }

    if (includeSpecialCharacter && !isIncludeSpecialCharacter(password.toString())) {
        return false
    }

    return true
}