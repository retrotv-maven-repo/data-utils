package dev.retrotv.data.utils;

import dev.retrotv.data.enums.OperatingSystem;
import org.apache.commons.codec.DecoderException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.RoundingMode;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    @DisplayName("hexToBase64 메소드 테스트")
    void test_hexStringToByteArray_method() throws DecoderException {
        String message = "The Quick Brown Fox Jumps Over The Lazy Dog";
        String hexString = ByteUtils.toHexString(StringUtils.toByteArray(message));
        String base64String = ByteUtils.toBase64String(StringUtils.toByteArray(message));

        assertEquals(hexString, StringUtils.base64ToHex(base64String));
        assertEquals(base64String, StringUtils.hexToBase64(hexString));
    }

    @Test
    @DisplayName("addNewLine(?) 메소드 테스트")
    void test_addNewLine_method1() {
        String os = System.getProperty("os.name").toLowerCase(Locale.getDefault());
        String value = "123456789";
        String returnValue = StringUtils.addNewLine(value);

        if (os.contains("win")) {
            assertEquals(value + "\r\n", returnValue);
        } else {
            assertEquals(value + "\n", returnValue);
        }
    }

    @Test
    @DisplayName("addNewLine(?, ?) 메소드 테스트")
    void test_addNewLine_method2() {
        OperatingSystem os = OperatingSystem.WINDOWS;
        String value = "123456789";
        String returnValue = StringUtils.addNewLine(value, os);

        assertEquals(value + "\r\n", returnValue);

        os = OperatingSystem.LINUX;
        returnValue = StringUtils.addNewLine(value, os);

        assertEquals(value + "\n", returnValue);
    }

    @Test
    @DisplayName("removeNewLine 메소드 테스트")
    void test_removeNewLine_method() {
        String value = "123456789\n";
        String returnValue = StringUtils.removeNewLine(value);

        assertNotEquals("123456789\n", returnValue);
        assertEquals("123456789", returnValue);
    }

    @Test
    @DisplayName("appendAll(?) 메소드 테스트")
    void test_appendAll_array_parameter_method() {
        String[] arr = {"Hello", "World"};
        String returnValue = StringUtils.appendAll(arr);

        assertNotEquals("Hello World", returnValue);
        assertEquals("HelloWorld", returnValue);
    }

    @Test
    @DisplayName("appendAll(?, ?, ?) 메소드 테스트")
    void test_appendAll_three_parameter_method() {
        String returnValue = StringUtils.appendAll(new String[]{"Hello", "World"}, " ", true);

        assertNotEquals("HelloWorld", returnValue);
        assertEquals("Hello World", returnValue);
    }

    @Test
    @DisplayName("masking 메소드 테스트")
    void test_masking_method() {
        String returnValue = StringUtils.masking("123456-1234567", '#', 8, 13);
        assertEquals("123456-1######", returnValue);

        returnValue = StringUtils.masking("123456-1234567", '#');
        assertEquals("##############", returnValue);
    }

    @Test
    @DisplayName("decimalToString(?, ?) 메소드 테스트")
    void test_decimalToString_two_parameter_method() {
        String returnValue = StringUtils.decimalToString((1.0/3.0), "#.##");
        assertEquals("0.33", returnValue);

        returnValue = StringUtils.decimalToString((5.0/3.0), "#.##");
        assertEquals("1.67", returnValue);
    }

    @Test
    @DisplayName("decimalToString(?, ?, ?) 메소드 테스트")
    void test_decimalToString_three_parameter_method() {
        String returnValue = StringUtils.decimalToString((1.0/3.0), "#.##", RoundingMode.UP);
        assertEquals("0.34", returnValue);

        returnValue = StringUtils.decimalToString((5.0/3.0), "#.##", RoundingMode.DOWN);
        assertEquals("1.66", returnValue);
    }

    @Test
    @DisplayName("intToString 메소드 테스트")
    void test_intToString_method() {
        String returnValue = StringUtils.intToString(1000000, "#,###");
        assertEquals("1,000,000", returnValue);

        returnValue = StringUtils.intToString(1000000, "#,###.00");
        assertEquals("1,000,000.00", returnValue);
    }

    @Test
    @DisplayName("scrambleChars 메소드 테스트")
    void test_scrambleChars_method() {
        System.out.println(StringUtils.scrambleChars("Hello, World!"));
    }

    @Test
    @DisplayName("combineString 메소드 테스트")
    void test_combineString_method() {
        String returnValue = StringUtils.combineStrings("Hello", "World");
        assertEquals("Hello World", returnValue);

        String[] arr = {"Hello", "World"};
        returnValue = StringUtils.combineStrings(arr, '.');
        assertEquals("Hello.World", returnValue);
    }

    @Test
    @DisplayName("isNull 메소드 테스트")
    void test_isNull_method() {
        assertTrue(StringUtils.isNull(null));
        assertFalse(StringUtils.isNull("Hello, World!"));
    }

    @Test
    @DisplayName("isEmpty 메소드 테스트")
    void test_isEmpty_method() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("Hello, World!"));
    }

    @Test
    @DisplayName("isBlank 메소드 테스트")
    void test_isBlank_method() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank(" "));
        assertFalse(StringUtils.isBlank("Hello, World!"));
    }

    @Test
    @DisplayName("trim 메소드 테스트")
    void test_trim_method() {
        String returnValue = StringUtils.trim(" Hello, World! ");
        assertEquals("Hello, World!", returnValue);

        returnValue = StringUtils.trim("Hello, World!");
        assertEquals("Hello, World!", returnValue);

        returnValue = StringUtils.trim(null);
        assertNull(returnValue);

        returnValue = StringUtils.trim(null, false);
        assertEquals("", returnValue);
    }

    @Test
    @DisplayName("strip 메소드 테스트")
    void test_strip_method() {
        String returnValue = StringUtils.strip(" Hello, World! ");
        assertEquals("Hello, World!", returnValue);

        returnValue = StringUtils.strip("Hello, World!");
        assertEquals("Hello, World!", returnValue);

        returnValue = StringUtils.strip(null);
        assertNull(returnValue);

        returnValue = StringUtils.strip(null, false);
        assertEquals("", returnValue);
    }
}
