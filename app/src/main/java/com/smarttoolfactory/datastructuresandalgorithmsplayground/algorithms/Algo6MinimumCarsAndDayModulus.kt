package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import java.util.*

/*
    1,4,1 -> [1,5,1]  = 2
    4,4,2,4 -> [5,5,2,5] = 3
    2,3,4,2 -> [2,5,7,2] = 2

 */
fun main() {
//    val day = solution("Wed", 2)
//
//    println("Day: $day")

//    val P = intArrayOf(1, 4, 1)
//    val S = intArrayOf(1, 5, 1)

    val P = intArrayOf(4, 4, 2, 4)
    val S = intArrayOf(5, 5, 2, 5)

    val carsNeeded = solution(P, S)
    println("Total cars: $carsNeeded")
}

fun solution(P: IntArray, S: IntArray): Int {

    val totalPeople = P.sum()
    var remainingPeople = totalPeople
    S.sortDescending()

    for (i in S.indices) {
        remainingPeople -= S[i]
        if (remainingPeople <= 0) {
            return i + 1
        }
    }

    return S.size
}

fun solution(S: String, K: Int): String {
    val reminder = (K + days[S]!!) % 7
    return when (reminder) {
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thu"
        5 -> "Fri"
        6 -> "Sat"
        else -> "Sun"

    }
}


val days: HashMap<String, Int> = hashMapOf(
    "Mon" to 1,
    "Tue" to 2,
    "Wed" to 3,
    "Thu" to 4,
    "Fri" to 5,
    "Sat" to 6,
    "Sun" to 7
)