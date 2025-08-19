package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.json.JSONObject;

class XMLUtilsJavaTest {
    
    @Test
    @DisplayName("XML to JSON 변환 테스트")
    void test_xmlToJson() {
        String xml =
              "<root>"
            +     "<name>John</name>"
            +     "<age>30</age>"
            +     "<city>New York</city>"
            + "</root>"
            ;
        JSONObject json = XMLUtils.xmlToJson(xml);
        assertEquals("John", json.getJSONObject("root").get("name"));
        assertEquals(30, json.getJSONObject("root").get("age"));
        assertEquals("New York", json.getJSONObject("root").get("city"));
    }
}

