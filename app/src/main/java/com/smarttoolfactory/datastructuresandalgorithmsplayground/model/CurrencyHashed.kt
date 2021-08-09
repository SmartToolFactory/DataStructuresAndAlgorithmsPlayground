package com.smarttoolfactory.datastructuresandalgorithmsplayground.model

class CurrencyHashed(currencyCode:String): Currency(currencyCode) {

    override fun equals(other: Any?): Boolean {

        if (other == null) return false

        return if (other::class.java == this::class.java) {
            this.currencyCode == (other as CurrencyHashed).currencyCode
        }else {
            return false
        }
    }

    override fun hashCode(): Int {
        return currencyCode.hashCode()
    }
}