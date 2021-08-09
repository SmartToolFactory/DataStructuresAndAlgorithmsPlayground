package com.smarttoolfactory.datastructuresandalgorithmsplayground.datastructures

import com.smarttoolfactory.datastructuresandalgorithmstutorials.model.Currency
import com.smarttoolfactory.datastructuresandalgorithmstutorials.model.CurrencyHashed

fun main() {
    val currency1 = Currency("USD")
    val currency2 = Currency("USD")

    val currencyHashed1 = CurrencyHashed("USD")
    val currencyHashed2 = CurrencyHashed("USD")

    /*
        Hashset also uses hashCode() and equals() implementation to contain elements in bucket
     */
    val set = hashSetOf(currency1,currencyHashed1)

    println("Contains currency: ${set.contains(currency2)}") // false
    println("Contains currency hashed: ${set.contains(currencyHashed2)}") // true
}