@file:JvmName("PasswordUtils")
package dev.retrotv.data.utils

@JvmOverloads
fun checkLength(password: CharSequence, minLength: Int = 8, maxLength: Int = 16): Boolean {
    return password.length in minLength..maxLength
}

@JvmOverloads
fun isInclude(
    password: CharSequence,
    includeEnglish: Boolean = false,
    includeLowerCaseEnglish: Boolean = true,
    includeUpperCaseEnglish: Boolean = true,
    includeNumber: Boolean = true,
    includeSpecialCharacter: Boolean = true
): Boolean {
    if (includeEnglish && !isIncludeEnglish(password.toString())) {
        return false
    }

    if (includeLowerCaseEnglish && !isIncludeLowerCase(password.toString())) {
        return false
    }

    if (includeUpperCaseEnglish && !isIncludeUpperCase(password.toString())) {
        return false
    }

    return if (includeNumber && !isIncludeNumber(password.toString())) {
        false
    } else !includeSpecialCharacter || isIncludeSpecialCharacter(password.toString())
}