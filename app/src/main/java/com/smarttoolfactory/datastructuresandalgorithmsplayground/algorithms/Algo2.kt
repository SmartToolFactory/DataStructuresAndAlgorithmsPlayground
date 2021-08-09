package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import java.util.*

fun main() {


    // 🤔 1480 Running Sum of 1d Array
//    val numsRunningArray = arrayOf(1, 1, 1, 1, 1)
//    val resRunningArray = runningSum(numsRunningArray.toIntArray()).asList()
//    println("Result: $resRunningArray")

    // 🤔 1470 Shuffle the Array
//    val shuffleArray = arrayOf(1, 2, 3, 4, 4, 3, 2, 1)
//    val resShuffleArray = shuffle(shuffleArray.toIntArray(), shuffleArray.size / 2).asList()
//    println("Result: $resShuffleArray")

    // 🤔1431 Kids With the Greatest Number of Candies
//    val candiesArray = arrayOf(2, 3, 5, 1, 3) // Extra 3
//    val resGreatestCandiesArray = kidsWithCandies(candiesArray.toIntArray(), 3).asList()
//    println("Result: $resGreatestCandiesArray")

    // 🤔 1512 Good Pairs
//    val pairsArray = arrayOf(1,2,3,1,1,3)
//    val pairsSum = numIdenticalPairs(pairsArray.toIntArray())
//    println("Result: $pairsSum")

    // 🤔1108. Defanging an IP Address
    val resIp4 = defangIPaddr("255.100.50.0")
    println("Result: $resIp4")

}

/**
 * 1480. Running Sum of 1d Array
 * https://leetcode.com/problems/running-sum-of-1d-array/
 *
Input: nums = [1,2,3,4]
Output: [1,3,6,10]
Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
Example 2:

Input: nums = [1,1,1,1,1]
Output: [1,2,3,4,5]
Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
 */
fun runningSum(nums: IntArray): IntArray {
    val size = nums.size

    for (index in 1 until size) {

        nums[index] = nums[index] + nums[index - 1]
    }

    return nums
}

/**
 * 1470. Shuffle the Array
 * https://leetcode.com/problems/shuffle-the-array/
 *
Given the array nums consisting of 2n elements in the form [x1,x2,...,xn,y1,y2,...,yn].

Return the array in the form [x1,y1,x2,y2,...,xn,yn].

Example 1:

Input: nums = [2,5,1,3,4,7], n = 3
Output: [2,3,5,4,1,7]
Explanation: Since x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 then the answer is [2,3,5,4,1,7].
Example 2:

Input: nums = [1,2,3,4,4,3,2,1], n = 4
Output: [1,4,2,3,3,2,4,1]
 */
fun shuffle(nums: IntArray, n: Int): IntArray {

    val newArray = IntArray(2 * n)

    for (i in 0 until n) {

        newArray[2 * i] = nums[i]
        newArray[2 * i + 1] = nums[n + i]

    }

    return newArray
}

/**
 * 1431. Kids With the Greatest Number of Candies
 * https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/
 *
Given the array candies and the integer extraCandies, where candies[i] represents the number
of candies that the ith kid has.

For each kid check if there is a way to distribute extraCandies among the kids such
that he or she can have the greatest number of candies among them. Notice that multiple
kids can have the greatest number of candies.



Example 1:

Input: candies = [2,3,5,1,3], extraCandies = 3
Output: [true,true,true,false,true]
Explanation:
Kid 1 has 2 candies and if he or she receives all extra candies (3) will have 5 candies
--- the greatest number of candies among the kids.

Kid 2 has 3 candies and if he or she receives at least 2 extra candies will have
the greatest number of candies among the kids.
Kid 3 has 5 candies and this is already the greatest number of candies among the kids.
Kid 4 has 1 candy and even if he or she receives all extra candies will only have 4 candies.
Kid 5 has 3 candies and if he or she receives at least 2 extra candies will have
the greatest number of candies among the kids.

Example 3:

Input: candies = [12,1,12], extraCandies = 10
Output: [true,false,true]
 */
fun kidsWithCandies(candies: IntArray, extraCandies: Int): BooleanArray {

    val resultArray = BooleanArray(candies.size)

    var max = 0
    for (i in candies.indices) {
        if (candies[i] > max) max = candies[i]
    }

    for (i in candies.indices) {
        resultArray[i] = (candies[i] + extraCandies >= max)
    }

    return resultArray
}

/**
 * 1512. Number of Good Pairs
 *
 *  https://leetcode.com/problems/number-of-good-pairs/
 *
Given an array of integers nums.

A pair (i,j) is called good if nums[i] == nums[j] and i < j.

Return the number of good pairs.



Example 1:

Input: nums = [1,2,3,1,1,3]
Output: 4
Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
Example 2:

Input: nums = [1,1,1,1]
Output: 6
Explanation: Each pair in the array are good.
Example 3:

Input: nums = [1,2,3]
Output: 0
 */
fun numIdenticalPairs(nums: IntArray): Int {

    val map = HashMap<Int, Int>()
    var sum = 0

    nums.forEach {
        map[it] = (map[it] ?: 0) + 1
    }

    map.forEach { (k, v) ->
        sum += (v * (v - 1) / 2)
    }

    return sum
}

/**
 * 1108. Defanging an IP Address
 *
 * https://leetcode.com/problems/defanging-an-ip-address/
 *
Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".

Example 1:

Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
Example 2:

Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"

Constraints:

The given address is a valid IPv4 address
 */
fun defangIPaddr(address: String): String {
//    return  address.replace(".","[.]")

    val sb = StringBuilder()

    address.forEach { char->

        if (char == '.') {
            sb.append("[.]")
        }else {
            sb.append(char)
        }
    }

    return sb.toString()
}