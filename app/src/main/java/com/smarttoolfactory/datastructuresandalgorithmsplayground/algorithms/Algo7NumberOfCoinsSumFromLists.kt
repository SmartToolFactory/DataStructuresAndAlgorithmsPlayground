package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms



fun main() {

//    val dividers = listOf(25, 10, 5, 1)
//    val coins = numberOfCoinsRecursive(94, 0, dividers)
//    println("Coins: $coins")

//    val coins = numberOfCoins(86, dividers)
//    println("Coins: $coins

    val list1 = listOf(2, 5, 3)
    val list2 = listOf(1, 4, 6)
    val equalToTarget = isSumEquals(list1, list2, target = 6)
    println("Target: $equalToTarget")
}

/*
Given the US coin system (25 cents, 10 cents, 5 cents, 1 cent) write an algorithm that
for a given integer N value provides a change in the minimal number of coins.

Input: N=86
Output: 1 1c, 0 5c, 1 10c, 3 25c
*/

fun numberOfCoinsRecursive(
    input: Int,
    index: Int = 0,
    dividers: List<Int> = listOf(25, 10, 5, 1),
    result: HashMap<Int, Int> = linkedMapOf()
): Map<Int, Int> {

    if (index < dividers.size) {
        val divider: Int = dividers[index]
        result[divider] = input / divider
        numberOfCoinsRecursive(input % divider, index + 1, dividers, result)
    }

    return result
}

fun numberOfCoins(
    input: Int,
    dividers: List<Int> = listOf(25, 10, 5, 1)
): Map<Int, Int> {

    var remainder = input
    val result = linkedMapOf<Int, Int>()

    dividers.forEach { divider ->
        val times = remainder / divider
        remainder %= divider
        result[divider] = times
    }

    return result
}

/*
    Find if sum of two numbers each from one list equals to target number
    [2,3,5], [7,1,4] with target 6 returns true for 2+4 = 6

 */
fun isSumEquals(list1: List<Int>, list2: List<Int>, target: Int): Boolean {
    val complements = hashSetOf<Int>()

    list1.forEach {
        complements.add(target - it)
    }

    list2.forEach {
        if (complements.contains(it)) return true
    }

    return false
}
