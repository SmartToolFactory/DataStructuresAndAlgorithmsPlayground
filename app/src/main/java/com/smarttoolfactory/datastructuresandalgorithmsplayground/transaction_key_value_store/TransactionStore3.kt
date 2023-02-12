package com.smarttoolfactory.datastructuresandalgorithmsplayground.transaction_key_value_store

import java.util.*
import kotlin.collections.ArrayList


fun main() {
    val transactionStore = TransactionStoreImpl3()

//    transactionStore.setValue("foo", "123")
//    val result = transactionStore.getValue("foo")
//    println("Result: $result")
//    transactionStore.delete("foo")
//    val result2 = transactionStore.getValue("foo")
//    println("Result2: $result2")

    /*
        Count the number of occurrences of a value
        > SET foo 123
        > SET bar 456
        > SET baz 123
        > COUNT 123
        2
        > COUNT 456
        1
     */
//    transactionStore.setValue("foo", "123")
//    transactionStore.setValue("bar", "456")
//    transactionStore.setValue("baz", "123")
//    val count1 = transactionStore.count("123")
//    val count2 = transactionStore.count("456")
//    println("Count1: $count1, count2: $count2")

    /*
        Commit a transaction
        > SET bar 123
        > GET bar
        123
        > BEGIN
        > SET foo 456
        > GET bar
        123
        > DELETE bar
        > COMMIT
        > GET bar
        key not set
        > ROLLBACK
        no transaction
        > GET foo
        456
     */
//    transactionStore.setValue("bar", "123")
//    val result = transactionStore.getValue("bar")
//    println("result: $result")
//    transactionStore.begin()
//    transactionStore.setValue("foo", "456")
//    val result2 = transactionStore.getValue("bar")
//    println("result2: $result2")
//    transactionStore.delete("bar")
//    transactionStore.commit()
//    val result3 = transactionStore.getValue("bar")
//    println("result3: $result3")
//    transactionStore.rollback()
//    val result4 = transactionStore.getValue("foo")
//    println("result4: $result4")
//    println("END...")

    /*
        Rollback a transaction
        > SET foo 123
        > SET bar abc
        > BEGIN
        > SET foo 456
        > GET foo
        456
        > SET bar def
        > GET bar
        def
        > ROLLBACK
        > GET foo
        123
        > GET bar
        abc
        > COMMIT
        no transaction
     */
//    transactionStore.setValue("foo", "123")
//    transactionStore.setValue("bar", "abc")
//    transactionStore.begin()
//    transactionStore.setValue("foo", "456")
//    val result = transactionStore.getValue("foo")
//    println("result: $result")
//    transactionStore.setValue("bar", "def")
//    val result2 = transactionStore.getValue("bar")
//    println("result2: $result2")
//    transactionStore.rollback()
//    val result3 = transactionStore.getValue("foo")
//    println("result3: $result3")
//    val result4 = transactionStore.getValue("bar")
//    println("result4: $result4")
//    transactionStore.commit()
//    println("END...")

    /*
        Nested transactions
        > SET foo 123
        > SET bar 456
        > BEGIN
        > SET foo 456
        > BEGIN
        > COUNT 456
        2
        > GET foo
        456
        > SET foo 789
        > GET foo
        789
        > ROLLBACK
        > GET foo
        456
        > DELETE foo
        > GET foo
        key not set
        > ROLLBACK
        > GET foo
        123
     */

    transactionStore.setValue("foo", "123")
    transactionStore.setValue("bar", "456")
    // NEW TRANSACTION...
    transactionStore.begin()
    transactionStore.setValue("foo", "456")
    // NEW TRANSACTION...
    transactionStore.begin()
    val count = transactionStore.count("456")
    println("COUNT: $count")
    val result = transactionStore.getValue("foo")
    println("result: $result")
    transactionStore.setValue("foo", "789")
    val result2 = transactionStore.getValue("foo")
    println("result2: $result2")
    // ROLLBACK...
    transactionStore.rollback()
    val result3 = transactionStore.getValue("foo")
    println("result3: $result3")
    transactionStore.delete("foo")
    val result4 = transactionStore.getValue("foo")
    println("result4: $result4")
    // ROLLBACK...
    transactionStore.rollback()
    val result5 = transactionStore.getValue("foo")
    println("result5: $result5")

    /*
        Random test
     */
//    transactionStore.setValue("foo", "123")
//    transactionStore.setValue("bar", "456")
//    transactionStore.begin()
//    transactionStore.setValue("row", "100")
//    transactionStore.begin()
//    transactionStore.setValue("apple", "red")
//    transactionStore.begin()
//    transactionStore.delete("foo")
//    transactionStore.commit()
//    val result1 = transactionStore.getValue("foo")
//    println("result1: $result1")
//    val result2 = transactionStore.getValue("apple")
//    println("result2: $result2")
//    transactionStore.rollback()
//    val result3 = transactionStore.getValue("apple")
//    println("result3: $result3")
//    val result4 = transactionStore.getValue("foo")
//    println("result4: $result4")
//    val result5 = transactionStore.getValue("row")
//    println("result5: $result5")
//    transactionStore.rollback()
//    val result6 = transactionStore.getValue("row")
//    println("result6: $result6")

}

internal data class NullableTransaction(
    var map: MutableMap<String, String?> = hashMapOf()
)

private class LocalDataSource {

    private val persistedStore: HashMap<String, String?> = hashMapOf()

    fun put(key: String, value: String?) {
        persistedStore[key] = value
    }

    fun getDataMap() = persistedStore.toMutableMap()
}

private class TransactionStoreImpl3 {

    val localDataStore = LocalDataSource()

    val transactionStack = LinkedList<NullableTransaction>().apply {
        add(NullableTransaction(localDataStore.getDataMap()))
    }

    val currentTransaction: NullableTransaction
        get() = transactionStack.last

    fun setValue(key: String, value: String) {
        currentTransaction.map[key] = value
    }

    fun getValue(key: String): String {
        val iterator = transactionStack.listIterator(transactionStack.size)

        while (iterator.hasPrevious()) {
            val map = iterator.previous().map
            val keyExists = map.containsKey(key)
            if (keyExists) {
                return map[key] ?: "key not set"
            }
        }
        return "key not set"
    }

    fun begin() {
        val transaction = NullableTransaction()
        transactionStack.add(transaction)
    }

    fun commit() {
        if (transactionStack.size > 1) {
            currentTransaction.map.forEach { (key, value) ->
                localDataStore.put(key, value)
                // Update parent transaction of current transaction before commit
                transactionStack.getOrNull(transactionStack.lastIndex - 1)?.map?.set(key, value)
            }
            transactionStack.removeLast()
        } else {
            println("> no transaction")
        }
    }

    fun rollback() {
        if (transactionStack.size > 1) {
            transactionStack.removeLast()
        } else {
            println("> No transaction")
        }
    }

    fun delete(key: String) {
        currentTransaction.map[key] = null
    }

    fun count(value: String): Int {
        var count = 0
        transactionStack.forEach {
            val map = it.map
            if (map.containsValue(value)) {
                count++
            }
        }

        return count
    }
}