package dev.retrotv.data.utils

import dev.retrotv.data.enums.OperatingSystem
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import kotlin.test.*
import java.math.RoundingMode
import java.util.*

class StringUtilsTest {

    @Test
    @DisplayName("addNewLine(?) 메소드 테스트")
    fun test_addNewLine_method1() {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())
        val value = "123456789"
        val returnValue = addNewLine(value)

        if (os.contains("win")) {
            assertEquals(value + "\r\n", returnValue)
        } else {
            assertEquals(value + "\n", returnValue)
        }
    }

    @Test
    @DisplayName("addNewLine(?, ?) 메소드 테스트")
    fun test_addNewLine_method2() {
        var os = OperatingSystem.WINDOWS
        val value = "123456789"
        var returnValue = addNewLine(value, os)

        assertEquals(value + "\r\n", returnValue)

        os = OperatingSystem.LINUX
        returnValue = addNewLine(value, os)

        assertEquals(value + "\n", returnValue)
    }

    @Test
    @DisplayName("removeNewLine 메소드 테스트")
    fun test_removeNewLine_method() {
        val value = "123456789\n"
        val returnValue = removeNewLine(value)

        assertNotEquals("123456789\n", returnValue)
        assertEquals("123456789", returnValue)
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    fun test_appendAll_array_parameter_method() {
        val arr = arrayOf("Hello", "World")
        val returnValue = appendAll(values = arr)

        assertNotEquals("Hello World", returnValue)
        assertEquals("HelloWorld", returnValue)
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    fun test_appendAll_dynamic_parameter_method() {
        val returnValue = appendAll("Hello", "World")

        assertNotEquals("Hello World", returnValue)
        assertEquals("HelloWorld", returnValue)
    }

    @Test
    @DisplayName("appendAll(?, ?, ?) 메소드 테스트")
    fun test_appendAll_three_parameter_method() {
        val returnValue = appendAll(values = arrayOf("Hello", "World"), delimiter = " ", useDelimiter = true)

        assertNotEquals("HelloWorld", returnValue)
        assertEquals("Hello World", returnValue)
    }

    @Test
    @DisplayName("masking 메소드 테스트")
    fun test_masking_method() {
        val returnValue = masking("123456-1234567", '#', 8, 13)
        assertEquals("123456-1######", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?) 메소드 테스트")
    fun test_decimalToString_two_parameter_method() {
        var returnValue = deciamlToString((1.0/3.0), "#.##")
        assertEquals("0.33", returnValue)

        returnValue = deciamlToString((5.0/3.0), "#.##")
        assertEquals("1.67", returnValue)
    }

    @Test
    @DisplayName("decimalToString(?, ?, ?) 메소드 테스트")
    fun test_decimalToString_three_parameter_method() {
        var returnValue = decimalToString((1.0/3.0), "#.##", RoundingMode.UP)
        assertEquals("0.34", returnValue)

        returnValue = decimalToString((5.0/3.0), "#.##", RoundingMode.DOWN)
        assertEquals("1.66", returnValue)
    }

    @Test
    @DisplayName("intToString 메소드 테스트")
    fun test_intToString_method() {
        var returnValue = intToString(1000000, "#,###")
        assertEquals("1,000,000", returnValue)

        returnValue = intToString(1000000, "#,###.00")
        assertEquals("1,000,000.00", returnValue)
    }

    @Test
    @DisplayName("isIncludeEnglish 메소드 테스트")
    fun test_isIncludeEnglish_method() {
        var returnValue = isIncludeEnglish("12가31b2나123354aslkwne1")
        assertTrue(returnValue)

        returnValue = isIncludeEnglish("가나다라11230345!@#!@$")
        assertFalse(returnValue)
    }

    @DisplayName("isEmail 메소드 테스트")
    fun test_isEmail_method() {
        var returnValue = isEmail("aaaaaaa@naver.com")
        assertTrue(returnValue)

        returnValue = isEmail("aaaaaaanaver.com")
        assertFalse(returnValue)

        returnValue = isEmail("qwsidmpawomqpwoqpwd")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeLowerCase 메소드 테스트")
    fun test_isIncludeLowerCase_method() {
        var returnValue = isIncludeLowerCase("12가31b2나123354aslkwne1")
        assertTrue(returnValue)

        returnValue = isIncludeLowerCase("가나다라11230345!@#!ASSDBWER@$")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeUpperCase 메소드 테스트")
    fun test_isIncludeUpperCase_method() {
        var returnValue = isIncludeUpperCase("12가31B2나123354ASDQEFG1")
        assertTrue(returnValue)

        returnValue = isIncludeUpperCase("가asd나다라112303fdb45!@#d!@$")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeNumber 메소드 테스트")
    fun test_isIncludeNumber_method() {
        var returnValue = isIncludeNumber("가31B2나123354ASDQEFG")
        assertTrue(returnValue)

        returnValue = isIncludeNumber("qwd가나다르바!@#d!@$")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeSpecialCharacter 메소드 테스트")
    fun test_isIncludeSpecialCharacter_method() {
        var returnValue = isIncludeSpecialCharacter("12가31b2!@#!나1233545asl&*)kwne1")
        assertTrue(returnValue)

        returnValue = isIncludeSpecialCharacter("가나다라11230345aslqASDQW")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isIncludeKorean 메소드 테스트")
    fun test_isIncludeKorean_method() {
        var returnValue = isIncludeKorean("b12가312나123354aslkwn!@#ee")
        assertTrue(returnValue)

        returnValue = isIncludeKorean("qwodnosfmlq2102983eome!@#rhmdlfbs")
        assertFalse(returnValue)
    }

    @Test
    @DisplayName("isPhone 메소드 테스트")
    fun test_isPhone_method() {
        var list = arrayOf(
            "02-000-0000",
            "02-0000-0000",
            "031-000-0000",
            "031-0000-0000",
            "032-000-0000",
            "032-0000-0000",
            "033-000-0000",
            "033-0000-0000",
            "041-000-0000",
            "041-0000-0000",
            "042-000-0000",
            "042-0000-0000",
            "043-000-0000",
            "043-0000-0000",
            "044-000-0000",
            "044-0000-0000",
            "051-000-0000",
            "051-0000-0000",
            "052-000-0000",
            "052-0000-0000",
            "053-000-0000",
            "053-0000-0000",
            "054-000-0000",
            "054-0000-0000",
            "055-000-0000",
            "055-0000-0000",
            "061-000-0000",
            "061-0000-0000",
            "062-000-0000",
            "062-0000-0000",
            "063-000-0000",
            "063-0000-0000",
            "064-000-0000",
            "064-0000-0000",
            "02)000-0000",
            "02)0000-0000",
            "031)000-0000",
            "031)0000-0000",
            "032)000-0000",
            "032)0000-0000",
            "033)000-0000",
            "033)0000-0000",
            "041)000-0000",
            "041)0000-0000",
            "042)000-0000",
            "042)0000-0000",
            "043)000-0000",
            "043)0000-0000",
            "044)000-0000",
            "044)0000-0000",
            "051)000-0000",
            "051)0000-0000",
            "052)000-0000",
            "052)0000-0000",
            "053)000-0000",
            "053)0000-0000",
            "054)000-0000",
            "054)0000-0000",
            "055)000-0000",
            "055)0000-0000",
            "061)000-0000",
            "061)0000-0000",
            "062)000-0000",
            "062)0000-0000",
            "063)000-0000",
            "063)0000-0000",
            "064)000-0000",
            "064)0000-0000",
            "020000000",
            "0200000000",
            "0310000000",
            "03100000000",
            "0320000000",
            "03200000000",
            "0330000000",
            "03300000000",
            "0410000000",
            "04100000000",
            "0410000000",
            "04200000000",
            "0430000000",
            "04300000000",
            "0440000000",
            "04400000000",
            "0510000000",
            "05100000000",
            "0520000000",
            "05200000000",
            "0530000000",
            "05300000000",
            "0540000000",
            "05400000000",
            "0550000000",
            "05500000000",
            "0610000000",
            "06100000000",
            "0620000000",
            "06200000000",
            "0630000000",
            "06300000000",
            "0640000000",
            "06400000000",
        )

        list.forEach { number -> assertTrue(isPhoneNumber(number)) }

        list = arrayOf(
            "01-000-0000",
            "01-0000-0000",
            "099-000-0000",
            "099-0000-0000",
            "034-000-0000",
            "034-0000-0000",
            "049-000-0000",
            "049-0000-0000",
            "057-000-0000",
            "057-0000-0000",
            "066-000-0000",
            "066-0000-0000",
            "077-000-0000",
            "077-0000-0000",
            "083-000-0000",
            "083-0000-0000",
            "012-000-0000",
            "012-0000-0000",
            "022-000-0000",
            "022-0000-0000",
            "030-000-0000",
            "030-0000-0000",
            "047-000-0000",
            "047-0000-0000",
            "059-000-0000",
            "059-0000-0000",
            "069-000-0000",
            "069-0000-0000",
            "068-000-0000",
            "068-0000-0000",
            "067-000-0000",
            "067-0000-0000",
            "050-000-0000",
            "050-0000-0000",
        )

        list.forEach { number -> assertFalse(isPhoneNumber(number)) }

        list = arrayOf(
            "010-0000-0000",
            "011-000-0000",
            "016-000-0000",
            "017-000-0000",
            "018-000-0000",
            "019-000-0000",
            "01000000000",
            "0110000000",
            "0160000000",
            "0170000000",
            "0190000000",
            "0190000000",
            "010)0000-0000",
            "011)000-0000",
            "016)000-0000",
            "017)000-0000",
            "018)000-0000",
            "019)000-0000",
            "010)00000000",
            "011)0000000",
            "016)0000000",
            "017)0000000",
            "018)0000000",
            "019)0000000",
        )

        list.forEach { number ->
            assertTrue(isPhoneNumber(number))
        }

        list = arrayOf(
            "012-000-0000",
            "013-000-0000",
            "014-000-0000",
            "015-000-0000",
            "0120000000",
            "0130000000",
            "0140000000",
            "0150000000",
            "012)000-0000",
            "013)000-0000",
            "014)000-0000",
            "015)000-0000",
            "012)0000000",
            "013)0000000",
            "014)0000000",
            "015)0000000"
        )

        list.forEach { number ->
            assertFalse(isPhoneNumber(number))
        }
    }

    @Test
    @DisplayName("isHomePhone 메소드 테스트")
    fun test_isHomePhone_method() {
        var list = arrayOf(
            "02-000-0000",
            "02-0000-0000",
            "031-000-0000",
            "031-0000-0000",
            "032-000-0000",
            "032-0000-0000",
            "033-000-0000",
            "033-0000-0000",
            "041-000-0000",
            "041-0000-0000",
            "042-000-0000",
            "042-0000-0000",
            "043-000-0000",
            "043-0000-0000",
            "044-000-0000",
            "044-0000-0000",
            "051-000-0000",
            "051-0000-0000",
            "052-000-0000",
            "052-0000-0000",
            "053-000-0000",
            "053-0000-0000",
            "054-000-0000",
            "054-0000-0000",
            "055-000-0000",
            "055-0000-0000",
            "061-000-0000",
            "061-0000-0000",
            "062-000-0000",
            "062-0000-0000",
            "063-000-0000",
            "063-0000-0000",
            "064-000-0000",
            "064-0000-0000",
            "02)000-0000",
            "02)0000-0000",
            "031)000-0000",
            "031)0000-0000",
            "032)000-0000",
            "032)0000-0000",
            "033)000-0000",
            "033)0000-0000",
            "041)000-0000",
            "041)0000-0000",
            "042)000-0000",
            "042)0000-0000",
            "043)000-0000",
            "043)0000-0000",
            "044)000-0000",
            "044)0000-0000",
            "051)000-0000",
            "051)0000-0000",
            "052)000-0000",
            "052)0000-0000",
            "053)000-0000",
            "053)0000-0000",
            "054)000-0000",
            "054)0000-0000",
            "055)000-0000",
            "055)0000-0000",
            "061)000-0000",
            "061)0000-0000",
            "062)000-0000",
            "062)0000-0000",
            "063)000-0000",
            "063)0000-0000",
            "064)000-0000",
            "064)0000-0000",
            "020000000",
            "0200000000",
            "0310000000",
            "03100000000",
            "0320000000",
            "03200000000",
            "0330000000",
            "03300000000",
            "0410000000",
            "04100000000",
            "0410000000",
            "04200000000",
            "0430000000",
            "04300000000",
            "0440000000",
            "04400000000",
            "0510000000",
            "05100000000",
            "0520000000",
            "05200000000",
            "0530000000",
            "05300000000",
            "0540000000",
            "05400000000",
            "0550000000",
            "05500000000",
            "0610000000",
            "06100000000",
            "0620000000",
            "06200000000",
            "0630000000",
            "06300000000",
            "0640000000",
            "06400000000",
        )

        list.forEach { number -> assertTrue(isHomePhoneNumber(number)) }

        list = arrayOf(
            "01-000-0000",
            "01-0000-0000",
            "099-000-0000",
            "099-0000-0000",
            "034-000-0000",
            "034-0000-0000",
            "049-000-0000",
            "049-0000-0000",
            "057-000-0000",
            "057-0000-0000",
            "066-000-0000",
            "066-0000-0000",
            "077-000-0000",
            "077-0000-0000",
            "083-000-0000",
            "083-0000-0000",
            "012-000-0000",
            "012-0000-0000",
            "022-000-0000",
            "022-0000-0000",
            "030-000-0000",
            "030-0000-0000",
            "047-000-0000",
            "047-0000-0000",
            "059-000-0000",
            "059-0000-0000",
            "069-000-0000",
            "069-0000-0000",
            "068-000-0000",
            "068-0000-0000",
            "067-000-0000",
            "067-0000-0000",
            "050-000-0000",
            "050-0000-0000",
        )

        list.forEach { number -> assertFalse(isHomePhoneNumber(number)) }
    }

    @Test
    @DisplayName("isCellPhone 메소드 테스트")
    fun test_isCellPhone_method() {
        var list = arrayOf(
            "010-0000-0000",
            "011-000-0000",
            "016-000-0000",
            "017-000-0000",
            "018-000-0000",
            "019-000-0000",
            "01000000000",
            "0110000000",
            "0160000000",
            "0170000000",
            "0190000000",
            "0190000000",
            "010)0000-0000",
            "011)000-0000",
            "016)000-0000",
            "017)000-0000",
            "018)000-0000",
            "019)000-0000",
            "010)00000000",
            "011)0000000",
            "016)0000000",
            "017)0000000",
            "018)0000000",
            "019)0000000",
        )

        list.forEach { number ->
            assertTrue(isCellPhoneNumber(number))
        }

        list = arrayOf(
            "012-000-0000",
            "013-000-0000",
            "014-000-0000",
            "015-000-0000",
            "0120000000",
            "0130000000",
            "0140000000",
            "0150000000",
            "012)000-0000",
            "013)000-0000",
            "014)000-0000",
            "015)000-0000",
            "012)0000000",
            "013)0000000",
            "014)0000000",
            "015)0000000"
        )

        list.forEach { number ->
            assertFalse(isCellPhoneNumber(number))
        }
    }

    @Test
    @DisplayName("scrambleChars 메소드 테스트")
    fun test_scrambleChars_method() {
        println(scrambleChars("Hello, World!"))
    }
}