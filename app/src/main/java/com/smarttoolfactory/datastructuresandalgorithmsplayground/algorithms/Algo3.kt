package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import kotlin.math.abs
import kotlin.system.measureTimeMillis


fun main() {



    /*
         TRIPLET SUM
     */
//    val sumArray = arrayOf(
//        4, 5, 10, 15, 16, 20, 29, 38, 48, 51, 55, 57,
//        60, 62, 71, 74, 79, 82, 93, 96, 98, 100, 200
//    )
//    val testArray = arrayOf(2, 4, 6, 8, 10)
//    var result = 0
//
//    val timePassed = measureTimeMillis {
//        result = findNumberOfTriplets(sumArray)
//    }
//    println("Result $result, time passed = $timePassed")

    /*
        TWO SUM
     */
//    val twoSumArray = arrayOf(1, 3, 5, 7, 8, 11, 15)
//    val target = 9
//
//    val twoSumResult = twoSum(twoSumArray, target)
////    val twoSumResult = twoSumHashMap(twoSumArray, target)
//    println("twoSumResult: [${twoSumResult[0]}, ${twoSumResult[1]}]")

    /*
        REVERSE INTEGER
     */

    val reverseInteger = reverseOfInteger(123)
    println("Reverse: $reverseInteger")

//    var swapArray = arrayOf(4, 3, 1, 2) // Swap count 3
    var swapArray = arrayOf(3, 2, 0, 1) // Swap count 3
    var swapArray2 = arrayOf(2, 3, 4, 1, 5) // Swap count 3
    var swapArray3 = arrayOf(7, 1, 3, 2, 4, 5, 6) // Swap count 5
    var swapArray4 = arrayOf(0, 6, 5, 7, 3, 1, 4, 2) // Swap count
    var swapArray5 = arrayOf(3, 4, 1, 2, 6, 7, 5) // Swap count 4


    var minSwap = 0
    val time = measureTimeMillis {
        minSwap = minimumSwaps2(swapArray)
    }

    println("Min SWAP: $minSwap, time: $time")


//    staircase(6)

}


/*
 * 🏪 Question1
 * Find number of triplets that sum of two is equal to third
 */

private fun findNumberOfTriplets(arr: Array<Int>): Int {

    var count = 0

    for (i in 2 until arr.size) {
        // Currently searching for sum of this number
        // for [2, 4, 6] sub array sum is 6

        val required = arr[i]

        var left = 0
        var right = i

//        println("START: left: $left, right: $right")

        for (j in 0 until i) {

            if (left >= right) break

            val sum = arr[left] + arr[right]

//            println("i: $i, j: $j, left: $left, right: $right, REQUIRED: $required, SUM: $sum")

            when {
                sum > required -> {
                    right--
//                    println("🥶 Lowering right: left: $left, right: $right")
                }
                sum < required -> {
                    left++
//                    println("🔥 Increasing left $left, right: $right")
                }
                else -> {
//                    println("🤨 FOUND arr[left]: ${arr[left]}, arr[right]: ${arr[right]}, required: $required")
                    // Decrease right to prevent duplicates while looping index j to index i
                    right--
                    count++
                }
            }


        }

    }

    return count
}

/*
    Given an array of integers, return indices of the two numbers such that they add up to a specific target.

    You may assume that each input would have exactly one solution, and you may not use the same element twice.

    Example:

    Given nums = [2, 7, 11, 15], target = 9,

    Because nums[0] + nums[1] = 2 + 7 = 9,
    return [0, 1].
 */

fun twoSum(nums: Array<Int>, target: Int): Array<Int> {

    var left = 0
    var right = nums.size - 1

    for (i in nums.indices) {

        val sum = nums[left] + nums[right]

        if (left >= right) throw ArithmeticException()

        if (sum == target) return arrayOf(left, right)


        when {
            sum < target -> left++
            sum > target -> right--
        }


    }

    return Array(2) { -1 }

}

fun twoSumHashMap(nums: Array<Int>, target: Int): Array<Int> {

    val sumMap = HashMap<Int, Int>()

    nums.forEachIndexed { index, value ->

        val complement = target - value

        println("index: $index, value: $value, complement: $complement")

        if (sumMap.contains(complement)) {
            println("Contains: $complement")
            return arrayOf(index, sumMap[complement]!!)
        }

        sumMap[value] = index

    }

    return Array(2) { -1 }
}

/**
 *
 * https://leetcode.com/problems/reverse-integer/
 *
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−231,  231 − 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 *
 * Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
 */
fun reverseOfInteger(num: Int): Int {

    var reversibleNum = abs(num)
    var reverse = 0
    var carry: Int


    while (reversibleNum != 0) {

        // Get carry
        carry = reversibleNum % 10

        reverse = reverse * 10 + carry

        // Iterate with num/10 to next digit
        reversibleNum /= 10

    }

    return reverse

}

// Complete the minimumSwaps function below.
fun minimumSwaps(arr: Array<Int>): Int {

    var swap = 0

    // 1, 3, 5, 2, 4, 6, 7
    // 4,1,3,2
    for (i in arr.indices) {

        var diff = 0
        var indexToSwap = i
        var j = i + 1

        while (j < arr.size) {

            if (arr[i] > arr[j]) {

                val newDiff = arr[i] - arr[j]

                if (diff < newDiff) {
                    diff = newDiff
                    indexToSwap = j
                }
            }


            j++
        }

        if (indexToSwap != i) {

            val temp = arr[i]
            arr[i] = arr[indexToSwap]
            arr[indexToSwap] = temp
            swap++

        }

    }


    return swap
}

fun minimumSwaps2(arr: Array<Int>): Int {

    // 7, 1, 3, 2, 4, 5, 6
    var swap = 0

    val visited = BooleanArray(arr.size)

    for (i in arr.indices) {

        println("For Start i: $i")
        var j = i

        var cycle = 0

        while (!visited[j]) {

            println("While j: $j")
            visited[j] = true
            cycle++

            // go to next element in array which is index that is 1 lower than current index
            // for instance arr[0[ = 7, j = 7-1 = 6 go to arr[6]
            if (j == 0) break


            j = arr[j] - 1


            println("arr[j] = ${arr[j]}, j: $j , cycle: $cycle ")


        }

        if (cycle != 0) swap += cycle - 1
    }
    return swap
}

fun minimumSwaps3(arr: Array<Int>): Int {

    var swapCount = 0

    val checkedArray = BooleanArray(arr.size)


    for (i in arr.indices) {

        if (checkedArray[i]) continue


    }



    return swapCount
}

/*

    for n = 6 print shape below
         # i = 0 j =           5
        ## i = 1 j =         4,5
       ### i = 2 j =       3,4,5
      #### i = 3 j =     2,3,4,5
     ##### i = 4 j =   1,2,3,4,5
    ###### i = 5 j = 0,1,2,3,4,5
 */
fun staircase(n: Int) {

    // n = 6

    for (i in 0 until n) {

        val j = n - i - 1
        var k = 1
        while (k < n + 1) {

            if (k > j) {
                print("#")
            } else {
                print(" ")
            }
            k++
        }
        // Move down
        println()
    }

}


/**
 * https://leetcode.com/problems/longest-common-prefix/
 *
 * Write a function to find the longest common prefix string amongst an array of strings.

    If there is no common prefix, return an empty string "".

    Example 1:

    Input: ["flower","flow","flight"]
    Output: "fl"
    Example 2:

    Input: ["dog","racecar","car"]
    Output: ""
    Explanation: There is no common prefix among the input strings.
    Note:

    All given inputs are in lowercase letters a-z.
 */
fun longestCommonPrefix(strs: Array<String>): String {

    // TODO
    var commonPrefix = ""



    return commonPrefix
}




