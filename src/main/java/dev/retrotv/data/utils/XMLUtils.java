package dev.retrotv.data.utils;

import lombok.NonNull;
import org.json.JSONObject;
import org.json.XML;

/**
 * XML 관련 유틸리티 클래스입니다.
 * <p>
 * 이 클래스는 XML 문자열을 JSON 객체로 변환하는 메서드를 제공합니다.
 * </p>
 *
 * @author  yjj8353
 * @since   1.0.0
 */
public final class XMLUtils {
    private XMLUtils() {
        throw new UnsupportedOperationException("XMLUtils는 인스턴스화 할 수 없습니다.");
    }

    public static JSONObject xmlToJson(@NonNull String xml) {
        return XML.toJSONObject(xml);
    }
}
