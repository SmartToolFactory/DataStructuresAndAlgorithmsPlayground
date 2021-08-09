package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

fun main() {
    val integers = listOf(2, 3, 4, 1, 2, 3, 2, 4, 3, 1, 2, 3)

    val newList = removeDuplicates(integers)

    println("New list: $newList")

    val initStr = "aabcdffd"
    val res = removeDuplicates(initStr)
    println("New Solution: $res")
}

fun <T> removeDuplicates(list: List<T>): List<T> {

    val newList = ArrayList<T>(list.size)

    val map = LinkedHashMap<T, Int>()

    for (i in list.indices) {
        val str = list[i] // O(1)
        // O(1)                 O(1)
        if (map[str] == null) map[str] = i
    }

    map.values.forEach {
        newList.add(list[it])
    }

    return newList
}

fun removeDuplicates(s: String): String? {

    var i = 0
    val n = s.length
    val res = s.toCharArray()
    var j = 0

    while (j < n) {
        res[i] = res[j]
        if (i > 0 && res[i - 1] == res[i]){
            println("res[i-1]: ${res[i - 1]}, res[i]: ${res[i]}, i:$i, j:$j")
            i -= 2
        }

        ++j
        ++i
    }
    return String(res, 0, i)
}
