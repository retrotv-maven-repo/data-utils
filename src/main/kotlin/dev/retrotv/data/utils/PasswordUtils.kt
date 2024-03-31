package dev.retrotv.data.utils

object PasswordUtils {

    @JvmStatic
    @JvmOverloads
    fun checkLength(password: CharSequence, minLength: Int = 8, maxLength: Int = 16): Boolean {
        return password.length in minLength..maxLength
    }

    @JvmStatic
    @JvmOverloads
    fun isInclude(
        password: CharSequence,
        includeLowerCaseEnglish: Boolean = true,
        includeUpperCaseEnglish: Boolean = true,
        includeNumber: Boolean = true,
        includeSpecialCharacter: Boolean = true
    ): Boolean {
        if (includeLowerCaseEnglish && !ValidUtils.isIncludeLowerCase(password.toString())) {
            return false
        }

        if (includeUpperCaseEnglish && !ValidUtils.isIncludeUpperCase(password.toString())) {
            return false
        }

        if (includeNumber && !ValidUtils.isIncludeNumber(password.toString())) {
            return false
        }

        if (includeSpecialCharacter && !ValidUtils.isIncludeSpecialCharacter(password.toString())) {
            return false
        }

        return true
    }
}
