package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import kotlin.math.abs

fun main() {


//    val str1 = "gainly"
//    val str2 = "laying"
//
//    val str1 = "listen"
//    val str2 = "silent"
//
//
//    val isAnagram = checkAnagram2(str1, str2)
//
//    println("Strings are anagram: $isAnagram")

    // 3 chars should be removed
//    val s1 = "bcadeh"
//    val s2 = "hea"
//
//    // 2 chars should be removed
//    val s1 = "cddgk"
//    val s2 = "gcd"
//
//    val numberOfChars = numberOfCharsToRemoveForAnagram(s1, s2)
//    println("Should remove $numberOfChars")


  val anagrams =  groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat"))

    println("Anagrams: $anagrams")
}


/**
 * Check if two strings are anagrams of each other
 */
fun checkAnagram(str1: String, str2: String): Boolean {

    val charMap = HashMap<Char, Int>()

    val length = str1.length
    if (length != str2.length) return false

    for (i in 0 until length) {
        charMap[str1[i]] = (charMap[str1[i]] ?: 0) + 1
        charMap[str2[i]] = (charMap[str2[i]] ?: 0) - 1
    }

    charMap.values.forEach { charCount: Int ->
        if (charCount != 0) {
            return false
        }
    }

    return true
}

fun checkAnagram2(str1: String, str2: String): Boolean {

    val charArray = IntArray(26)

    val length = str1.length
    if (length != str2.length) return false

    for (i in str1) {
        charArray[i - 'a']++
    }

    for (i in str2) {
        charArray[i - 'a']--
    }

    charArray.forEach { charCount: Int ->
        if (charCount != 0) {
            return false
        }
    }

    return true
}

/**
 * Calculate number of chars to remove for 2 strings to become anagrams of each other.
 */
fun numberOfCharsToRemoveForAnagram(str1: String, str2: String): Int {

    val charMap = HashMap<Char, Int>()

    for (i in str1.indices) {
        charMap[str1[i]] = (charMap[str1[i]] ?: 0) + 1
    }

    for (i in str2.indices) {
        charMap[str2[i]] = (charMap[str2[i]] ?: 0) - 1
    }

    var count = 0

    charMap.values.forEach { charCount: Int ->
        count += abs(charCount)
    }

    return count
}

/**
 * Returns List that contains list of anagrams. En each list there
 *  are strings that are anagram of each other
 *
 *  @param strs words are that not in any order
 */
fun groupAnagrams(strs: Array<String>): List<List<String>> {

    val anagramMap = HashMap<String, MutableList<String>>()

    strs.forEach { str ->

        val charArray = str.toCharArray().sortedArray()
        val sorted = String(charArray)

        println("🎃 str: $str, sorted: $sorted")

        if (anagramMap[sorted] == null) {
            anagramMap[sorted] = mutableListOf()
        }

        anagramMap[sorted]?.apply {
            add(str)
        }
    }

    val list = mutableListOf<MutableList<String>>()

    anagramMap.forEach {
        list.add(it.value)
    }

    return list
}