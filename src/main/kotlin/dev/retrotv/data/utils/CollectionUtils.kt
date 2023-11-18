@file:JvmName("CollectionUtils")
package dev.retrotv.data.utils

/**
 * List에서 중복되는 데이터를 제거하고 반환합니다.
 * orgOrder를 true로 설정할 경우 반환 List가 원본 List와 동일한 순서로 유지되며, 중복 데이터는 첫번째 값만 유지됩니다.
 *
 * @param values 중복을 제거할 list
 * @param orgOrder 원본 데이터와의 동일한 순서 유지여부
 * @return 중복이 제거된 list
 */
fun <T> removeDuplicates(values: List<T>, orgOrder: Boolean = false): List<T> {
    var newValues = values.toSet().toMutableList()

    if (orgOrder) {
        val orderValues = mutableListOf<T>()
        values.forEach { org ->
            newValues.forEachIndexed { i, new ->
                if (org == new) {
                    orderValues.add(newValues.removeAt(i))
                    return@forEach
                }
            }
        }

        newValues = orderValues
    }

    return newValues.toList()
}
