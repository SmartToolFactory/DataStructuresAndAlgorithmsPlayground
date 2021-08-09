package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import java.text.DecimalFormat

/**
 * Question from Booking.com interview
 */
fun main() {

    val lineItems: Array<Array<String>> = arrayOf(
        arrayOf("Rental", "190", "EUR"),
        arrayOf("GPS", "15.00", "GBP"),
        arrayOf("Insurance", "30.00", "USD")
    )

    val exchangeRates: Array<Array<String>> = arrayOf(
        arrayOf("GBP", "0.70"),
        arrayOf("USD", "1.20"),
    )

    val currencySymbols: Array<Array<String>> = arrayOf(
        arrayOf("GBP", "£"),
        arrayOf("EUR", "€"),
        arrayOf("USD", "$"),
    )

    val result = printPriceSummary(lineItems, exchangeRates, currencySymbols)

    println("Results: $result")
}

class Conversion(val type: String, val amount: String, val ticker: String) {
    var rate: String = "1.00"
    var totalInEuros: String = "0.00"
    var symbol: String = "€"
}

fun printPriceSummary(
    lineItems: Array<Array<String>>,
    exchangeRates: Array<Array<String>>,
    currencySymbols: Array<Array<String>>
): String {

    val list = ArrayList<Conversion>()

    val tickerToSymbolMap = HashMap<String, String>()
    val tickerToRateMap = HashMap<String, String>()

    lineItems.forEach { lineItem: Array<String> ->
        val conversion = Conversion(lineItem[0], lineItem[1], lineItem[2])
        list.add(conversion)
    }

    exchangeRates.forEach { exchangeRate: Array<String> ->
        tickerToRateMap[exchangeRate[0]] = exchangeRate[1]
    }

    currencySymbols.forEach { currencySymbol: Array<String> ->
        tickerToSymbolMap[currencySymbol[0]] = currencySymbol[1]
    }

    return getResultString(list, tickerToRateMap, tickerToSymbolMap)
}

private fun getResultString(
    list: ArrayList<Conversion>,
    tickerToRateMap: HashMap<String, String>,
    tickerToSymbolMap: HashMap<String, String>
): String {
    val sb = StringBuilder()
    var total = 0.0
    var isOneNotInEuros = false
    val decimalFormat = DecimalFormat("#.00")

    list.forEach { conversion ->

        tickerToRateMap[conversion.ticker]?.let {
            conversion.rate = it
        }

        tickerToSymbolMap[conversion.ticker]?.let {
            conversion.symbol = it
        }

        conversion.totalInEuros =
            decimalFormat.format(conversion.amount.toDouble() / conversion.rate.toDouble())

        val result =
            if (conversion.ticker != "EUR") {
                isOneNotInEuros = true
                "(${conversion.symbol}${conversion.amount}) €${conversion.totalInEuros} approx."
            } else "€${conversion.totalInEuros}"

        total += conversion.totalInEuros.toDouble()

        sb.append("${conversion.type}: $result\n")
    }

    sb.append("\nTOTAL: €${decimalFormat.format(total)}")
    if (isOneNotInEuros) sb.append(" approx.")
    return sb.toString()
}