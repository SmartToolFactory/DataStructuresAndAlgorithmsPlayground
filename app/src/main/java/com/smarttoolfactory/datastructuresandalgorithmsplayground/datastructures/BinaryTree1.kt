package com.smarttoolfactory.datastructuresandalgorithmsplayground.datastructures

fun main() {
    val root = initTree()

//    val treeHeight = maxDepth(root)
//    println("Tree height: $treeHeight")

//    println("🔥IN ORDER")
//    root.printPreOrder()
//    println("\n🔥PRE-ORDER")
//    root.printPreOrder()
//    println("\n🔥POST ORDER")
//    root.printPostOrder()

    printLeftView(root,1)

    println("\n********************")
//    val anotherRoot = TreeNode(15)
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

private fun initTree(): TreeNode {
    /*
                15
             /      \
            8        20
          /  \      /  \
         5    10  17   28
        /\
       3  6
    */

    val root = TreeNode(15)

    root.left = TreeNode(8)
    root.left?.left = TreeNode(5)
    root.left?.right = TreeNode(10)

    root.left?.left?.left = TreeNode(3)
    root.left?.left?.right = TreeNode(6)

    root.right = TreeNode(20)
    root.right?.left = TreeNode(17)
    root.right?.right = TreeNode(28)
    return root
}

class TreeNode(val data: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

fun TreeNode.insert(value: Int) {

    // Check left of the node
    if (value < data) {
        if (left == null) {
            left = TreeNode(value)
        } else {
            left?.insert(value)
        }
    } else {

        // Check right of the node
        if (right == null) {
            right = TreeNode(value)
        } else {
            right?.insert(value)
        }
    }
}

fun TreeNode.containsValue(value: Int): Boolean {

    if (value == data) return true

    // Check for left side of the current node if it's not null, if it's null return false
    return if (value < data) {
        left?.containsValue(value) ?: false
    } else {
        // Check for right side of the current node if it's not null, if it's null return false
        right?.containsValue(value) ?: false
    }
}


class BinaryTree(var root: TreeNode?) {


}

/* Compute the "maxDepth" of a tree -- the number of
   nodes along the longest path from the root node
   down to the farthest leaf node.*/
fun maxDepth(node: TreeNode?, left: Boolean = true): Int {

    /*
                15
             /      \
            8        20
          /  \      /  \
         5    10  17   28
        /\
       3  6
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


    println("✏️ After CHECK NODE data: ${node.data}, " + if (left) "LEFT" else "RIGHT")
    /* compute the depth of each subtree */
    val lDepth = maxDepth(node.left)
    println("🔥 maxDepth() lDepth: $lDepth, NODE data: ${node.data}, " + if (left) "LEFT" else "RIGHT")

    val rDepth = maxDepth(node.right, false)

    println("🚀 maxDepth() Left depth: $lDepth, right depth: $rDepth, NODE data: ${node.data}, " + if (left) "LEFT" else "RIGHT")

    return 1 + lDepth.coerceAtLeast(rDepth)
}


fun maximumDepth(root: TreeNode?, level: Int = 0): Int {

    if (root == null) return level

    var currentLevel: Int = level

    currentLevel++

    val leftDepth = maximumDepth(root.left, currentLevel)
    val rightDepth = maximumDepth(root.right, currentLevel)

    return leftDepth.coerceAtMost(rightDepth)
}


/*

https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
        1
       / \
      2   3
     / \
    4   5

    Depth First Traversals:
    (a) Inorder (Left, Root, Right) : 4 2 5 1 3
    (b) Preorder (Root, Left, Right) : 1 2 4 5 3
    (c) Postorder (Left, Right, Root) : 4 5 2 3 1

    Breadth First or Level Order Traversal : 1 2 3 4 5

    TREE Traversals
        5
       / \
      3   6

    🔥 InOrder: 3->5->6
    Uses of Inorder
    In case of binary search trees (BST), Inorder traversal gives nodes in non-decreasing order.
    To get nodes of BST in non-increasing order,
    a variation of Inorder traversal where Inorder traversal s reversed can be used.

    🔥 Preorder: 5->3->6
    Preorder traversal is used to create a copy of the tree.
    Preorder traversal is also used to get prefix expression on of an expression tree.
    Please see http://en.wikipedia.org/wiki/Polish_notation to know why prefix expressions are useful.

    🔥 PostOrder: 3->6->5
    Uses of Postorder
    Postorder traversal is used to delete the tree.
    Please see the question for deletion of tree for details.
    Postorder traversal is also useful to get the postfix expression of an expression tree.
    Please see http://en.wikipedia.org/wiki/Reverse_Polish_notation to
    for the usage of postfix expression.
 */

fun TreeNode.printInOrder() {
    left?.printInOrder()
    print("$data- ")
    right?.printInOrder()

}

fun TreeNode.printPreOrder() {
    print("$data- ")
    left?.printPreOrder()
    right?.printPreOrder()

}

fun TreeNode.printPostOrder() {
    left?.printPostOrder()
    right?.printPostOrder()
    print("$data- ")
}


/* Given a binary tree, print its nodes in inorder*/
fun printInorder(node: TreeNode?) {
    if (node == null) return

    /* first recur on left child */
    printInorder(node.left)

    /* then print the data of node */
    print("${node.data}- ")

    /* now recur on right child */
    printInorder(node.right)
}

/* Given a binary tree, print its nodes in preorder*/
fun printPreorder(node: TreeNode?) {
    if (node == null) return

    /* first print data of node */
    print("${node.data}- ")

    /* then recur on left subtree */
    printPreorder(node.left)

    /* now recur on right subtree */
    printPreorder(node.right)
}

/* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
fun printPostorder(node: TreeNode?) {
    if (node == null) return

    // first recur on left subtree
    printPostorder(node.left)

    // then recur on right subtree
    printPostorder(node.right)

    // now deal with the node
    print("${node.data}- ")
}

var maxLevel = 0

fun printLeftView(node: TreeNode?, level: Int,  left:Boolean = true) {

    if (node == null) return


    println("Node: ${node.data}, maxLevel: $maxLevel, level: $level, LEFT: $left")

    if (maxLevel < level) {
        println("🔥 Data on left node: ${node.data}, LEFT: $left")
        maxLevel = level
    }

    println("AFTER level: $level, currentLevel: $maxLevel")
    println("********************")

    printLeftView(node.left, level + 1, true)
    printLeftView(node.right, level + 1, false)

}