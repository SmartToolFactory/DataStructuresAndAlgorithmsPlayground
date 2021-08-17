package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    val node = initNode()
//    node.printNodes()

//    println("REVERSED")
//    reverseLinkedList(node)
//    node.printNodes()
    val node2 = reverseLinkedListRecursively(node)
//    node2?.printNodes()

//    val indexOf = findIndexOfElement(node, 2)
//    println("Index of element 2 is: $indexOf")


//    val elementCountAfterThis = findElementCountAfterIndex(node, 5)
//    println("Total: $elementCountAfterThis nodes after this")
//    val elementCountAfterThisRecursive = findElementCountAfterIndexRecursive(node, 5)
//    println("Total: $elementCountAfterThisRecursive nodes after this RECURSIVE")
}


fun reverseLinkedList(head: Node) {
    var prev: Node? = null
    var current: Node? = head

    while (current != null) {
        val nextTemp = current.next
        current.next = prev
        prev = current
        current = nextTemp
    }
}

fun reverseLinkedListRecursively(head: Node?): Node? {

    if (head?.next == null) return head

    println("HEAD: ${head.data}")
    val p: Node? = reverseLinkedListRecursively(head.next)
   println("P: ${p?.data}")

    head.next?.next = head
    head.next = null

    p?.printNodes()
    return p
}




fun findIndexOfElement(node: Node?, item: Int): Int {
    if (node == null) return -1
    if (node.data == item) return item
    return findIndexOfElement(node.next, item)
}

fun findElementCountAfterIndex(node: Node?, item: Int): Int {

    var index = -1

    var current = node
    var counter = 0
    while (current?.next != null) {

        if (current.data == item) {
            index = counter
        }

        counter++
        current = current.next
    }

    if (index == -1) return -1

    return counter - index
}

fun findElementCountAfterIndexRecursive(
    node: Node?,
    item: Int,
    itemIndex: Int = -1,
    currentIndex: Int = 0
): Int {

    var hitIndex = itemIndex

    if (node?.next == null) {
        return if (hitIndex < 0) hitIndex else currentIndex - hitIndex
    }

    if (node.data == item) {
        hitIndex = currentIndex
    }


    return findElementCountAfterIndexRecursive(node.next, item, hitIndex, currentIndex + 1)
}


fun Node.printNodes() {

    val sb = StringBuilder()
    sb.append("+++++++++++++++++\n")

    sb.append("Level 0, data: $data\n")
    var level = 1

    var nextNode = next

    while (nextNode != null) {
        sb.append("Level $level, data: ${nextNode.data}\n")
        nextNode = nextNode.next
        level++
    }
    sb.append("*****************\n")
    println(sb.toString())
}

private fun initNode(): Node {
    val head = Node(1)
    head.next = Node(2)
    head.next?.next = Node(3)
    head.next?.next?.next = Node(4)
    head.next?.next?.next?.next = Node(5)
    head.next?.next?.next?.next?.next = Node(6)
    head.next?.next?.next?.next?.next?.next = Node(7)
    head.next?.next?.next?.next?.next?.next?.next = Node(8)
    head.next?.next?.next?.next?.next?.next?.next?.next = Node(9)
    return head
}

class Node(var data: Int) {
    var next: Node? = null
}
