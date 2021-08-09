package com.smarttoolfactory.datastructuresandalgorithmsplayground.datastructures

import com.smarttoolfactory.datastructuresandalgorithmstutorials.model.Currency
import com.smarttoolfactory.datastructuresandalgorithmstutorials.model.CurrencyHashed
import java.util.*

fun main() {
    // 🔥🔥🔥Hash Map KEYS should both meet the conditions of EQUALS and same HASH CODE
    /*
        HashMap uses hashCode(), == and equals() for entry lookup.
        The lookup sequence for a given key k is as follows:

        Use k.hashCode() to determine which bucket the entry is stored, if any
        If found, for each entry's key k1 in that bucket, if k == k1 || k.equals(k1), then return k1's entry
        Any other outcomes, no corresponding entry
     */

    val currency1 = CurrencyHashed("USD")
    val currency2 = CurrencyHashed("USD")

    println("Currency1: " + currency1 + ", hashCode: " + currency1.hashCode())
    println("Currency2: " + currency2 + ", hashCode: " + currency2.hashCode())

    println("Currency1 equals Currency2: " + (currency1 == currency2))

    // 🔥 This never returns TRUE even when both equals method and hashcode is overridden to be same
    // Only when both objects are same
    println("Currency1 == Currency2: " + (currency1 === currency2))


    val rates = HashMap<Currency, Double>()

    rates[currency1] = 1.1

    // 🔥🔥🔥Hash Map KEYS should both meet the conditions of EQUALS and same HASH CODE
    /*
            HashMap uses hashCode(), == and equals() for entry lookup.
            The lookup sequence for a given key k is as follows:

            Use k.hashCode() to determine which bucket the entry is stored, if any
            If found, for each entry's key k1 in that bucket, if k == k1 || k.equals(k1), then return k1's entry
            Any other outcomes, no corresponding entry
         */
    val result = rates[currency2]

    println("Result: $result")
}