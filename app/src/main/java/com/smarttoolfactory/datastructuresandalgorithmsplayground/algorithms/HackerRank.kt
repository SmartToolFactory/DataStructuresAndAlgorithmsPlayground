package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {

    /**
     *   ResEven = (A[0]* [2] + A[4] * A[6] + A[8]) % 2
     *   ResOdd = (A[1]* [3] + A[5] * A[7]) % 2
     *
     *   ResEven > ResOdd = EVEN
     *   ResEven < ResOdd = ODD
     *   ResEven = ResOdd = NEUTRAL
     */

//    val testArray = arrayOf(10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10) // ODD
////    val testArray = arrayOf(12, 3, 5, 7, 13, 12) // NEUTRAL
//
//    val res = plusMultSingleLoop(testArray)
//
//    println("RES: $res")

    /**
     * Substring of a string
     * xyz = x, xy, xyz, y, yz, z in ascending order
     *
     *
     * each char distinct
     * 1<s.kength<16
     */

    val testStr = "xyzt"
    val resSubStrArray = buildSubSequences(testStr).toList()
    print("resSubStrArray: $resSubStrArray")
}


fun plusMult(A: Array<Int>): String {

    var even = 0
    var odd = 0
    val size = A.size

    val arrayEven = arrayOfNulls<Int>(size / 2 + 1)
    val arrayOdd = arrayOfNulls<Int>(size / 2 + 1)

    var iEven = 0
    var iOdd = 0

    for (index in 0 until size) {

        if (index % 2 == 0) {
            arrayEven[iEven] = A[index]
            iEven++

        } else {
            arrayOdd[iOdd] = A[index]
            iOdd++
        }

    }

    even = collectArrays(arrayEven)
    odd = collectArrays(arrayOdd)

    odd %= 2
    even %= 2

    return when {
        odd > even -> "ODD"
        odd > even -> "EVEN"
        else -> "NEUTRAL"
    }
}

fun collectArrays(arr: Array<Int?>): Int {

    val newArray = arr.filterNotNull()

    var sum = 0
    val arraySize = newArray.size


    for (i in newArray.indices) {

        if (i % 2 == 1) {
            sum += newArray[i - 1] * newArray[i]
        }
    }

    // Add last element that is not paired
    if (arraySize % 2 == 1)
        sum += newArray[arraySize - 1]

    return sum
}

fun plusMultSingleLoop(A: Array<Int>): String {

    var even = 0
    var odd = 0
    val size = A.size

    var iEven = 0
    var iOdd = 0

    for (index in 0 until size) {

        if (index % 2 == 0) {

            iEven++

            if (iEven % 2 == 0) {
                even += A[index - 2] * A[index]
            } else if (iEven % 2 == 1 && index + 2 >= size) {
                // Add last element after pair A[0] * A[2] + [A4], A[4] is added here
                even += A[index]
            }

        } else {

            iOdd++

            if (iOdd % 2 == 0) {
                odd += A[index - 2] * A[index]
            } else if (iOdd % 2 == 1 && index + 2 >= size) {
                // Add last element after pair A[1] * A[3] + [A5], A[5] is added here
                odd += A[index]
            }
        }
    }

    odd %= 2
    even %= 2

    return when {
        odd > even -> "ODD"
        odd > even -> "EVEN"
        else -> "NEUTRAL"
    }
}

fun buildSubSequences(s: String): Array<String> {

    /*
        For xyzt input
        1- generate xyzt, yzt, zt, t sub-string in first loop
        2- in second loop xyzt generate x, xy, xyt, xytz in ASCENDING sorted order

     */

    val list = ArrayList<String>()

    val set = HashSet<String>()

    Arrays.sort(s.toCharArray())

    for (i in s.indices) {

        for (j in (i until s.length)) {

            println("🚌 i: $i, j: $j")

            val subString = s.substring(i, j + 1)

            // xyzt
            println("🤔 Res: $subString")

            if (!set.contains(subString)) set.add(subString)
            val sb = StringBuilder(subString)

            for (k in 1 until subString.length) {

                println("Inner sub k: $k, str:$sb")

                sb.deleteCharAt(sb.length - 1)
                if (!set.contains(subString)) set.add(sb.toString())
            }
        }

    }
    return set.toSortedSet().toTypedArray()

}
