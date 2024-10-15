package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals

class XMLUtilsTest {

    @Test
    @DisplayName("XML to JSON 변환 테스트")
    fun test_xmlToJson() {
        val xml = """
            <root>
                <name>John</name>
                <age>30</age>
                <city>New York</city>
            </root>
        """.trimIndent()

        val json = XMLUtils.xmlToJson(xml)

        assertEquals("John", json.getJSONObject("root").get("name"))
        assertEquals(30, json.getJSONObject("root").get("age"))
        assertEquals("New York", json.getJSONObject("root").get("city"))
    }
}