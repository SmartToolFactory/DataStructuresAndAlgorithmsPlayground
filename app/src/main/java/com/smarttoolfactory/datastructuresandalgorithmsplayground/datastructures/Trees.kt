package com.smarttoolfactory.datastructuresandalgorithmsplayground.datastructures

fun main() {
    val root = initTree()

    val treeHeight = maxDepth(root)
    println("Tree height: $treeHeight")

//    root.printInOrder()

    println("********************")
//    val anotherRoot = Node(15)
//
//    // height: 1
//    anotherRoot.insert(8)
//    anotherRoot.insert(20)
//
//    // height: 2
//    // Left
//    anotherRoot.insert(5)
//    anotherRoot.insert(10)
//    // Right
//    anotherRoot.insert(17)
//    anotherRoot.insert(28)
//
//    println("Another root contains 17 -> ${anotherRoot.containsValue(7)}")
//    println("Another root contains 17 -> ${anotherRoot.containsValue(20)}")

}

private fun initTree(): Node {
    /*
                    15
                 /      \
                8        20
              /  \      /  \
            5   10     17   28
           /\
          3 6
    */

    val root = Node(15)

    root.left = Node(8)
    root.left?.left = Node(5)
    root.left?.right = Node(10)

    root.left?.left?.left = Node(3)
    root.left?.left?.right = Node(6)

    root.right = Node(20)
    root.right?.left = Node(17)
    root.right?.right = Node(28)
    return root
}

class Node(val data: Int) {
    var left: Node? = null
    var right: Node? = null
}

fun Node.insert(value: Int) {

    // Check left of the node
    if (value < data) {
        if (left == null) {
            left = Node(value)
        } else {
            left?.insert(value)
        }
    } else {

        // Check right of the node
        if (right == null) {
            right = Node(value)
        } else {
            right?.insert(value)
        }
    }
}

fun Node.containsValue(value: Int): Boolean {

    if (value == data) return true

    // Check for left side of the current node if it's not null, if it's null return false
    return if (value < data) {
        left?.containsValue(value) ?: false
    } else {
        // Check for right side of the current node if it's not null, if it's null return false
        right?.containsValue(value) ?: false
    }
}

fun Node.printInOrder() {

    if (left != null) {
        left?.printInOrder()
    }

    println("$data")

    if (right != null) {
        right?.printInOrder()
    }
}


class BinaryTree(var root: Node?) {


}

/* Compute the "maxDepth" of a tree -- the number of
   nodes along the longest path from the root node
   down to the farthest leaf node.*/
fun maxDepth(node: Node?, left: Boolean = true): Int {

    /*
                15
             /      \
            8        20
          /  \      /  \
        5   10     17   28
       /\
      3 6
    Only LEFT recursion
    ✅ maxDepth() NODE data: 15, LEFT
    ✏️ After CHECK NODE data: 15, LEFT
    ✅ maxDepth() NODE data: 8, LEFT
    ✏️ After CHECK NODE data: 8, LEFT
    ✅ maxDepth() NODE data: 5, LEFT
    ✏️ After CHECK NODE data: 5, LEFT
    ✅ maxDepth() NODE data: 3, LEFT
    🔥 maxDepth() lDepth: 0, NODE data: 5, LEFT
    🔥 maxDepth() lDepth: 1, NODE data: 8, LEFT
    🔥 maxDepth() lDepth: 2, NODE data: 15, LEFT
    Tree height: 3
    ********************

    Only RIGHT recursion
    ✅ maxDepth() NODE data: 15, LEFT
    ✏️ After CHECK NODE data: 15, LEFT
    ✅ maxDepth() NODE data: 20, RIGHT, left NULL: false, right NULL: false
    ✏️ After CHECK NODE data: 20, RIGHT
    ✅ maxDepth() NODE data: 28, RIGHT, left NULL: true, right NULL: true
    🔥 maxDepth() lDepth: 0, NODE data: 20, RIGHT
    🔥 maxDepth() lDepth: 1, NODE data: 15, LEFT
    Tree height: 2
    ********************

    LEFT->RIGHT Recursion
    ✅ maxDepth() NODE data: 15, LEFT
    ✏️ After CHECK NODE data: 15, LEFT
    ✅ maxDepth() NODE data: 8, LEFT
    ✏️ After CHECK NODE data: 8, LEFT
    ✅ maxDepth() NODE data: 5, LEFT
    ✏️ After CHECK NODE data: 5, LEFT
    ✅ maxDepth() NODE data: 3, LEFT
    🔥 maxDepth() lDepth: 0, NODE data: 5, LEFT
    ✅ maxDepth() NODE data: 6, RIGHT, left NULL: true, right NULL: true
    🚀 maxDepth() Left depth: 0, right depth: 0, NODE data: 5, LEFT
    🔥 maxDepth() lDepth: 1, NODE data: 8, LEFT
    ✅ maxDepth() NODE data: 10, RIGHT, left NULL: true, right NULL: true
    🚀 maxDepth() Left depth: 1, right depth: 0, NODE data: 8, LEFT
    🔥 maxDepth() lDepth: 2, NODE data: 15, LEFT
    ✅ maxDepth() NODE data: 20, RIGHT, left NULL: false, right NULL: false
    ✏️ After CHECK NODE data: 20, RIGHT
    ✅ maxDepth() NODE data: 17, LEFT
    🔥 maxDepth() lDepth: 0, NODE data: 20, RIGHT
    ✅ maxDepth() NODE data: 28, RIGHT, left NULL: true, right NULL: true
    🚀 maxDepth() Left depth: 0, right depth: 0, NODE data: 20, RIGHT
    🚀 maxDepth() Left depth: 2, right depth: 1, NODE data: 15, LEFT
    Tree height: 3
    ********************

*/

    println(
        "✅ maxDepth() NODE data: ${node?.data}, " +
                if (left) "LEFT" else "RIGHT, " +
                        "left NULL: ${node?.left == null}, " +
                        "right NULL: ${node?.right == null}"
    )

    if (node?.left == null && node?.right == null)
        return 0

    // 🔥 This one return +1 because we count the leaf nodes
//    if(node == null) return 0


    println("✏️ After CHECK NODE data: ${node.data}, " +  if (left) "LEFT" else "RIGHT")
    /* compute the depth of each subtree */
    val lDepth =  maxDepth(node.left)
    println("🔥 maxDepth() lDepth: $lDepth, NODE data: ${node.data}, " +  if (left) "LEFT" else "RIGHT")

    val rDepth = maxDepth(node.right, false)

    println("🚀 maxDepth() Left depth: $lDepth, right depth: $rDepth, NODE data: ${node.data}, " +  if (left) "LEFT" else "RIGHT")

    return 1 + lDepth.coerceAtLeast(rDepth)
}


fun maximumDepth(root: Node?, level: Int = 0): Int {

    if (root == null) return level

    var currentLevel: Int = level

    currentLevel++

    val leftDepth =  maximumDepth(root.left, currentLevel)
    val rightDepth = maximumDepth(root.right, currentLevel)

    return leftDepth.coerceAtMost(rightDepth)
}
