package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse

class ValidUtilsTest {

    @Test
    @DisplayName("isDate 메소드 테스트")
    fun test_isDate_method() {
        assertTrue(ValidUtils.isDate("20230911"))
        assertFalse(ValidUtils.isDate("20230229"))

        assertTrue(ValidUtils.isDate("2023-09-11"))
        assertFalse(ValidUtils.isDate("2023-02-29"))
    }

    @org.junit.jupiter.api.Test
    @DisplayName("isIncludeEnglish 메소드 테스트")
    fun test_isIncludeEnglish_method() {
        var returnValue = ValidUtils.isIncludeEnglish("12가31b2나123354aslkwne1")
        assertTrue(returnValue)

        returnValue = ValidUtils.isIncludeEnglish("가나다라11230345!@#!@$")
        assertFalse(returnValue)
    }

    @DisplayName("isEmail 메소드 테스트")
    fun test_isEmail_method() {
        var returnValue = ValidUtils.isEmail("aaaaaaa@naver.com")
        assertTrue(returnValue)

        returnValue = ValidUtils.isEmail("aaaaaaanaver.com")
        assertFalse(returnValue)

        returnValue = ValidUtils.isEmail("qwsidmpawomqpwoqpwd")
        assertFalse(returnValue)
    }

    @org.junit.jupiter.api.Test
    @DisplayName("isIncludeLowerCase 메소드 테스트")
    fun test_isIncludeLowerCase_method() {
        var returnValue = ValidUtils.isIncludeLowerCase("12가31b2나123354aslkwne1")
        assertTrue(returnValue)

        returnValue = ValidUtils.isIncludeLowerCase("가나다라11230345!@#!ASSDBWER@$")
        assertFalse(returnValue)
    }

    @org.junit.jupiter.api.Test
    @DisplayName("isIncludeUpperCase 메소드 테스트")
    fun test_isIncludeUpperCase_method() {
        var returnValue = ValidUtils.isIncludeUpperCase("12가31B2나123354ASDQEFG1")
        assertTrue(returnValue)

        returnValue = ValidUtils.isIncludeUpperCase("가asd나다라112303fdb45!@#d!@$")
        assertFalse(returnValue)
    }

    @org.junit.jupiter.api.Test
    @DisplayName("isIncludeNumber 메소드 테스트")
    fun test_isIncludeNumber_method() {
        var returnValue = ValidUtils.isIncludeNumber("가31B2나123354ASDQEFG")
        assertTrue(returnValue)

        returnValue = ValidUtils.isIncludeNumber("qwd가나다르바!@#d!@$")
        assertFalse(returnValue)
    }

    @org.junit.jupiter.api.Test
    @DisplayName("isIncludeSpecialCharacter 메소드 테스트")
    fun test_isIncludeSpecialCharacter_method() {
        var returnValue = ValidUtils.isIncludeSpecialCharacter("12가31b2!@#!나1233545asl&*)kwne1")
        assertTrue(returnValue)

        returnValue = ValidUtils.isIncludeSpecialCharacter("가나다라11230345aslqASDQW")
        assertFalse(returnValue)
    }

    @org.junit.jupiter.api.Test
    @DisplayName("isIncludeKorean 메소드 테스트")
    fun test_isIncludeKorean_method() {
        var returnValue = ValidUtils.isIncludeKorean("b12가312나123354aslkwn!@#ee")
        assertTrue(returnValue)

        returnValue = ValidUtils.isIncludeKorean("qwodnosfmlq2102983eome!@#rhmdlfbs")
        assertFalse(returnValue)
    }

    @org.junit.jupiter.api.Test
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

        list.forEach { number -> assertTrue(ValidUtils.isPhoneNumber(number)) }

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

        list.forEach { number -> assertFalse(ValidUtils.isPhoneNumber(number)) }

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
            assertTrue(ValidUtils.isPhoneNumber(number))
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
            assertFalse(ValidUtils.isPhoneNumber(number))
        }
    }

    @org.junit.jupiter.api.Test
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

        list.forEach { number -> assertTrue(ValidUtils.isHomePhoneNumber(number)) }

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

        list.forEach { number -> assertFalse(ValidUtils.isHomePhoneNumber(number)) }
    }

    @org.junit.jupiter.api.Test
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
            assertTrue(ValidUtils.isCellPhoneNumber(number))
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
            assertFalse(ValidUtils.isCellPhoneNumber(number))
        }
    }
}