package dev.retrotv.data.utils

import org.junit.jupiter.api.DisplayName
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.asserter

class CollectionUtilsTest {

    @Test
    @DisplayName("removeDuplicates 메소드 테스트")
    fun test_removeDuplicates_method() {
        val values: MutableList<String> = emptyList<String>().toMutableList()
        values.add("2")
        values.add("1")
        values.add("3")
        values.add("5")
        values.add("4")
        values.add("1")
        values.add("4")
        values.add("2")
        assertEquals(values.size, 8)

        val removedDuplicates = removeDuplicates(values, true)
        assertEquals(removedDuplicates.size, 5)

        // 순서 보장 테스트
        val testValues: MutableList<String> = emptyList<String>().toMutableList()
        testValues.add("2")
        testValues.add("1")
        testValues.add("3")
        testValues.add("5")
        testValues.add("4")
        removedDuplicates.forEachIndexed { i, s -> assertEquals(s, testValues[i]) }
    }

    @Test
    @DisplayName("isDuplicated 메소드 테스트")
    fun test_isDuplicated_method() {
        val values: MutableList<String> = emptyList<String>().toMutableList()
        values.add("1")
        values.add("2")
        values.add("3")
        values.add("4")
        values.add("5")

        asserter.assertTrue("중복된 데이터가 존재합니다.", !isDuplicated(values))

        values.add("1")

        asserter.assertTrue("중복된 데이터가 존재하지 않습니다.", isDuplicated(values))
    }
}