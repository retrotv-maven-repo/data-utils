package dev.retrotv.data.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtilsTest {

    @Test
    @DisplayName("removeDuplicates 메소드 테스트")
    void test_removeDuplicates_method() {
        List<String> values = new ArrayList<>();
        values.add("2");
        values.add("1");
        values.add("3");
        values.add("5");
        values.add("4");
        values.add("1");
        values.add("4");
        values.add("2");
        assertEquals(8, values.size());

        List<String> removedDuplicates = CollectionUtils.removeDuplicates(values, true);
        assertEquals(5, removedDuplicates.size());

        // 순서 보장 테스트
        List<String> testValues = new ArrayList<>();
        testValues.add("2");
        testValues.add("1");
        testValues.add("3");
        testValues.add("5");
        testValues.add("4");
        
        for (int i = 0; i < removedDuplicates.size(); i++) {
            assertEquals(testValues.get(i), removedDuplicates.get(i));
        }
    }

    @Test
    @DisplayName("isDuplicated 메소드 테스트")
    void test_isDuplicated_method() {
        List<String> values = new ArrayList<>();
        values.add("1");
        values.add("2");
        values.add("3");
        values.add("4");
        values.add("5");

        assertFalse(CollectionUtils.isDuplicated(values), "중복된 데이터가 존재하지 않습니다.");

        values.add("1");

        assertTrue(CollectionUtils.isDuplicated(values), "중복된 데이터가 존재합니다.");
    }
}
