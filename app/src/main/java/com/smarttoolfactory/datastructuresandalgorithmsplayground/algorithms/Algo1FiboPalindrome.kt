package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms.TestFunctions.Companion.palindromeInt


fun main() {


    /**** FACTORIAL ****/
//    val factRes = factorialIterative(5)
//    println("factRes: $factRes")
//    val factResult = factorialTailRecursive(5)
//    println("Factorial result: $factResult")
//
    /**** FIBONACCI ****/
//    val fiboResult = fibonacci(8)
//    println("Fibonacci RECURSIVE Result: $fiboResult")
//
//    val fiboResultLoop = fibonacciWithLoop(8)
//    println("Fibonacci LOOP Result: $fiboResultLoop")
//
//
//    val timeFiboRecursive = measureNanoTime {
//        fibonacci(30)
//    }
//
//    println("Fibonacci Recursive Time: $timeFiboRecursive")
//
//    val timeFiboWithLoop = measureNanoTime {
//        fibonacciWithLoop(30)
//    }
//
//    println("Fibonacci Loop Time: $timeFiboWithLoop")
//
//
//    val fibonacciResultDynamic = fibonacciWithMemoization(7)
//    println("fibonacciResultDynamic: $fibonacciResultDynamic")

    /**** PALINDROME ****/
//    val resPalindromeString = palindrome("ABCCBA")
//    println("Palindrome: $resPalindromeString")
//
//    val resPalindromeRecursive = palindromeRecursive("ABCCBA")
//
//    println("Palindrome Recursive: $resPalindromeRecursive")

    val result = palindromeInt(-115511)
    println("Palindrome Int: $result")
}

class TestFunctions {

    companion object {

        private val fibonacciMap = HashMap<Int, Int>()

        fun factorialIterative(num: Int): Int {

            if (num == 0 || num == 1) return 1
            var result = 1

            for (i in 2..num) {
                result *= i
            }

            return result

        }


        /**
         * *** FACTORIAL ***
         */
        // Recursive function that is optimized with tailRec keyword
        fun factorialTailRecursive(x: Int): Int {

            tailrec fun factTail(y: Int, z: Int): Int {
                println("FactTail x: $x, y: $y, z: $z")
                return if (y == 1) z else factTail(y - 1, y * z)
            }

            return factTail(x, 1)

        }


        /**
         * *** FIBONACCI ***
         */


        /**
         * Time complexity O(2^n)
         * Space complexity O(n)
         */
        fun fibonacci(index: Int): Int {


            return when {
                index < 0 -> throw IllegalArgumentException("Index cannot be lower than 0")
                index in 0..1 -> index
                else -> fibonacci(index - 1) + fibonacci(index - 2)
            }

        }

        /**
         * Time complexity O(n)
         * Space complexity O(1) only result field is used for storing output
         */
        fun fibonacciWithLoop(index: Int): Int {


            if (index == 0 || index == 1) return index

            // Low, High
            // Index    0,  1,  2,  3,  4,  5,  6, 7
            // Result   0,  1,  1,  2,  3,  5,  8, 13
            var result = 0

            var low = 0
            var high = 1

            for (i in 2..index) {
                result = low + high

                low = high
                high = result
            }

            return result
        }

        /**
         * Time complexity O(n) if result is never calculated, otherwise O(1)
         * Space complexity O(n)
         */
        fun fibonacciWithMemoization(index: Int): Int {

            if (fibonacciMap.containsKey(index)) {
                return fibonacciMap[index]!!
            }

            if (index <= 1) {
                return index
            }

            var low = 0
            var high = 1
            var sum = 0

            for (i in 2..index) {
                sum = low + high
                low = high
                high = sum
                if (!fibonacciMap.containsKey(i)) fibonacciMap[i] = sum
            }
            return sum
        }

        /**
         * *** PALINDROME ***
         */

        fun palindromeSimple(text: String): Boolean = (text == text.reversed())

        /**
         * Time complexity O(n)
         * Space complexity O(1)
         */
        fun palindromeShort(text: String): Boolean {
            val length = text.length
            for (index in 0 until length) if (text[index] != text[length - index - 1]) return false
            return true
        }

        /**
         * Time complexity O(n)
         * Space complexity O(1)
         */
        fun palindrome(text: String): Boolean {

            val charArray = text.toCharArray()

            val size = charArray.size

            charArray.forEachIndexed { index, c ->
                if (c != charArray[size - index - 1]) return false
            }

            return true
        }


        /**
         * Time complexity O(n)
         * Space complexity O(n), if implemented recursively,
         * it needs to store the call to the method on a stack. This may require O(n) space.
         */
        fun palindromeRecursive(text: String): Boolean {

            val length = text.length
            if (length <= 1) return true

            if (text.first() == text.last()) {
                return palindromeRecursive(
                    text.substring(1, length - 1)
                )
            }

            return false
        }

        fun palindromeInt(number: Int): Boolean {
            // Cases 1 0 1, 9 3 9, 12 21, 43534

            var divided = number
            var reverse = 0
            var reminder = 0

            while (divided != 0) {
                reminder = divided.rem(10)
                reverse = reverse * 10 + reminder
                divided /= 10
            }

            return reverse == number
        }

    }
}