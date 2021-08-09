package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

/**
 * Find smallest common element between 2 lists
 */
fun main() {

    val list1 = listOf(5, 3, 12, 4, 6, 15, 46, 7, 7, 6, 12)
    val list2 = listOf(65, 33, 12, 4, 26, 15, 12, 46, 3)

    val common = findSmallestCommonElement2(list1, list2)
    println("Smallest common element: $common")

}

/**
 * Finds smallest common element between 2 [Integer] lists. If there is no common element returns -1
 */
fun findSmallestCommonElement(list1: List<Int>, list2: List<Int>): Int {

    val sortedList = list1.sorted()

    val commons: Set<Int> = sortedList.intersect(list2)

    if (commons.isNotEmpty()) {
        var smallestCommonElement = Integer.MAX_VALUE
        commons.forEach {
            smallestCommonElement = smallestCommonElement.coerceAtMost(it)
        }

        return smallestCommonElement
    }

    return -1
}

fun findSmallestCommonElement2(list1: List<Int>, list2: List<Int>): Int {

    val sortedList = list1.sorted()

    val map = HashMap<Int, Int>()

    list2.forEach {
        map[it] = 0
    }

    sortedList.forEach {
        map[it]?.let { num ->
            map[it] = num + 1
        }
    }

    if (map.size > 0) {
        var smallestCommonElement = Integer.MAX_VALUE
        map.keys.forEach { key ->
            if (map[key] != 0) {
                smallestCommonElement = smallestCommonElement.coerceAtMost(key)
            }
        }

        return smallestCommonElement
    }

    return -1
}
