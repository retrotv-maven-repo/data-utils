package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class NullUtilsTest {

    @Test
    @DisplayName("생성자 호출 방지 테스트")
    void test_constructor_blocked() throws Exception {
        Constructor<NullUtils> constructor = NullUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);

        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        Throwable cause = exception.getCause();

        assertInstanceOf(UnsupportedOperationException.class, cause);
        assertEquals("NullUtils는 인스턴스를 생성할 수 없습니다.", cause.getMessage());
    }

    @Test
    @DisplayName("isNull() 메서드 테스트")
    void test_isNull_method() {
        assertTrue(NullUtils.isNull(null));

        Object obj = new Object();
        assertFalse(NullUtils.isNull(obj));
    }
}
