package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

fun main() {

    // Fibonacci
//    val res = fibonacciRecursive(4)
//    print("Fibonacci res: $res")

    val sumRecursive = sum(4)
    println("Sum: $sumRecursive")

}


/*
    Index: 0 1 2 3 4 5 6 7
    Total: 0 1 1 2 3 5 8 13
 */
fun fibonacciRecursive(n: Int, sec: Boolean = false): Int {

    println("Running for num: $n, is second: $sec")

    if (n == 0 || n == 1) return n

    println("Continue...")
    val result = fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2, true)

    println("🍎 Result: $result, second: $sec")
    return result

    /*
        Prints:
        Running for num: 4, is second: false
        Continue...
        Running for num: 3, is second: false
        Continue...
        Running for num: 2, is second: false
        Continue...
        Running for num: 1, is second: false
        Running for num: 0, is second: true
        🍎 Result: 1, second: false
        Running for num: 1, is second: true
        🍎 Result: 2, second: false
        Running for num: 2, is second: true
        Continue...
        Running for num: 1, is second: false
        Running for num: 0, is second: true
        🍎 Result: 1, second: true
        🍎 Result: 3, second: false
        Fibonacci res: 3


     */
}

fun sumTailRecursive(index: Int, currentIndex: Int = 1, sum: Int = 0): Int {

    println("sumRecursive() currentIndex: $currentIndex, sum: $sum")
    return if (index < currentIndex) {
        println("🚀 SUM: $sum")
        // 🔥 This is the result return for first recursive block in else, depending
        // on result of this function n times call starting from end to start of recursion
        // it's returned, result of initial call to sumRecursive() is returned as result.

        // For instance if n is 4, first call returns result, after returning 3 times
        // last return of sumRecursive() in else block is the actual result
        sum
    } else {
        println("🤔 Going recursive currentIndex: $currentIndex, sum: $sum")
        val result = sumTailRecursive(index, currentIndex + 1, sum + currentIndex)
        println("🔥 RECURSIVE result: $result")
        result
    }
}

fun sumTailRecursive2(n: Int, sum: Int = 0): Int {

    if (n == 0) {
        println("🚀 SUM: $sum")
        return sum
    }

    val result = sumTailRecursive2(n - 1, sum + n)
    println("🔥 RECURSIVE result: $result")
    return result
}

fun sum(n: Int): Int {
    return if (n >= 1) {
        println("🚀 sum() START n: $n")
        val result =   sum(n - 1) + n
        println("🔥 sum() n: $n, result: $result")
        result
    } else {
        println("🍏 sum() Return n: $n")
         n
    }

    /*
        🚀 sum() START n: 4
        🚀 sum() START n: 3
        🚀 sum() START n: 2
        🚀 sum() START n: 1
        🍏 sum() Return n: 0
        🔥 sum() n: 1, result: 1
        🔥 sum() n: 2, result: 3
        🔥 sum() n: 3, result: 6
        🔥 sum() n: 4, result: 10
        Sum: 10
     */
}

fun tailSum(currentSum: Int, n: Int): Int {
    return if (n <= 1) {
        currentSum + n
    } else tailSum(currentSum + n, n - 1)
}

