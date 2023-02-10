package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms


fun main() {

//    val result = solution(123456)
    // 79856 // 49179
    val result = solution(79856)
    println("RESULT: $result")

//    val employees = arrayOf(3, 4, 3, 0, 2, 2, 3, 0, 0)
//    val employees = arrayOf(5,4,3,3,1,0)
//    val employees = arrayOf(5, 4, 1)
//    val reportCount = getNumberOfSuperiors(employees)
//    println("Report count: $reportCount")

//    val finalString = limitMessage("Codility We test coders", 18)
//    println("Final String: $finalString😀")

}

//fun solution(number: Int): Int {
//    var divided = number
//    var currentNumber = 0
//    var digit = 0
//
//    while (divided != 0) {
//        val currentDigit = calculateCurrentDigit(divided)
//
//        currentNumber = (currentNumber + currentDigit* 10f.pow(digit)).toInt()
//
//        divided /= 10
//        digit++
//    }
//    return currentNumber
//}

private fun calculateCurrentDigit(divided: Int): Int {
    var currentDigit = divided % 10

    currentDigit *= currentDigit

    while (currentDigit > 9) {
        val r = currentDigit / 10
        val c = currentDigit % 10
        currentDigit = r + c
    }

    return currentDigit
}

fun solution(number: Int): Int {
    var divided = number
    var str = ""

    while (divided != 0) {
        val currentDigit = calculateCurrentDigit(divided)

        str = "$currentDigit$str"

        divided /= 10
    }
    return str.toInt()

}


fun getNumberOfSuperiors(ranks: Array<Int>): Int {

    val workersMap = hashMapOf<Int, Int>()

    // [3, 4, 3, 0, 2, 2, 3, 0, 0] = 5
    // rank 3/count 3 - rank 4/count 1 - rank2/count 2
    ranks.forEach { rank ->
        val count = workersMap[rank] ?: 0
        workersMap[rank] = count + 1
    }

    var count = 0
    workersMap.keys.forEach { rank ->
        val superiorRank = rank + 2
        if (workersMap.contains(superiorRank)) {
            count += workersMap[rank]!!
        }
    }

    return count
}

fun limitMessage(message: String, limit: Int): String {

    if (limit >= message.length) return message

    val words = message.split(" ")
    var index = 0

    words.forEach { word ->
        val wordLength = word.length

        if (index + wordLength <= limit) {
            index += wordLength + 1
        } else {
            return@forEach
        }
    }

    // remove space at the end
    index -= 1

    return message.substring(0, index)
}