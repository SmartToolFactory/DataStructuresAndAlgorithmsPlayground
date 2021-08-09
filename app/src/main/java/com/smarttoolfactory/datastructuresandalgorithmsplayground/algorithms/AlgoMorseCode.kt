package com.smarttoolfactory.datastructuresandalgorithmsplayground.algorithms

fun main() {

    val str = "?.??"

    val chars: List<Char> = getMorseChars(str)
    chars.forEach {
        println("🎃CHAR: $it")
    }

    val result: List<Char> =  getMorseCharsRecursive(str)

    result.forEach {
        println( "Possible CHAR: $it")
    }

}

/**
 *
 * [https://upload.wikimedia.org/wikipedia/commons/c/ca/Morse_code_tree3.png]
 *
 * Recursively traverses binary tree to get valid character for [signals] input.
 *
 * If there is a transmission error due to one or multiple chars being [?] Traverses
 * both left and right of the current note
 *
 * @param signals Sequence of [*], [-], or [?]
 * @param nodes Contain the character in a specific node in tree. There can be variants of
 * result due to missing signals
 * @param index of the char in [signals]
 * @param rootNode is either root node or the current node depending [index]
 *
 * @return [List<Char>] that contains possible outcomes depending on the input.
 */
fun getMorseCharsRecursive(
    signals: String,
    nodes: MutableList<Char> = mutableListOf(),
    index: Int = 0,
    rootNode: MorseTreeNode? = null,
): List<Char> {

    val node = rootNode ?: getParentNode()

    if (index < signals.length) {

        when (signals[index]) {
            '.' -> {
                getMorseCharsRecursive(signals, nodes, index + 1, node.left)
            }
            '-' -> {
                getMorseCharsRecursive(signals, nodes, index + 1, node.right)
            }
            '?' -> {
                getMorseCharsRecursive(signals, nodes, index + 1, node.left)
                getMorseCharsRecursive(signals, nodes, index + 1, node.right)
            }
        }

    } else {
        nodes.add(node.char?:'?')
    }

    return nodes
}


fun getMorseChars(str: String): List<Char> {
    val rootNode = getParentNode()
    val chars = mutableListOf<Char>()
    getValidChars(str, chars, rootNode)
    return chars
}

/**
 * Returns [List<Char>] by looping each char in [signals], if it hits **?** then rescursively
 * moves for ***left*** and ***right*** branches.
 *
 * ## Note: This function uses mix of loop and recursion.
 */
private fun getValidChars(
    signals: String,
    list: MutableList<Char> = mutableListOf(),
    rootNode: MorseTreeNode? = null
) {

    var parentNode = rootNode ?: getParentNode()
    var currentNode = parentNode

    signals.forEachIndexed { index, signal ->
        when (signal) {
            '.' -> {
                parentNode.left?.let {
                    currentNode = it
                }
            }
            '-' -> {
                parentNode.right?.let {
                    currentNode = it
                }
            }
            '?' -> {
                // Create new string after index of ?
                val str = signals.substring(index + 1)
                getValidChars(".$str", list, parentNode)
                getValidChars("-$str", list, parentNode)

                return
            }
        }

        parentNode = currentNode
    }

    currentNode.char?.let { char ->
        list.add(char)
    }
}


class MorseTreeNode(val char: Char?) {
    var left: MorseTreeNode? = null
    var right: MorseTreeNode? = null

}

fun getParentNode(): MorseTreeNode {
    val parentNode = MorseTreeNode(null)

    // First level
    val e = MorseTreeNode('E')
    val t = MorseTreeNode('T')

    parentNode.left = e
    parentNode.right = t

    // Second level
    val i = MorseTreeNode('I')
    val a = MorseTreeNode('A')
    e.left = i
    e.right = a

    val n = MorseTreeNode('N')
    val m = MorseTreeNode('M')
    t.left = n
    t.right = m

    // Third level
    val s = MorseTreeNode('S')
    val u = MorseTreeNode('U')
    i.left = s
    i.right = u

    val r = MorseTreeNode('R')
    val w = MorseTreeNode('W')
    a.left = r
    a.right = w

    val d = MorseTreeNode('D')
    val k = MorseTreeNode('K')
    n.left = d
    n.right = k

    val g = MorseTreeNode('G')
    val o = MorseTreeNode('O')
    m.left = g
    m.right = o

    // Forth Level
    val h = MorseTreeNode('H')
    val v = MorseTreeNode('V')
    s.left = h
    s.right = v

    val f = MorseTreeNode('F')
    val u1 = MorseTreeNode('Ü')
    u.left = f
    u.right = u1

    val l = MorseTreeNode('L')
    val a1 = MorseTreeNode('Ä')
    r.left = l
    r.right = a1

    val p = MorseTreeNode('P')
    val j = MorseTreeNode('J')
    w.left = p
    w.right = j

    val b = MorseTreeNode('B')
    val x = MorseTreeNode('X')
    d.left = b
    d.right = x

    val c = MorseTreeNode('C')
    val y = MorseTreeNode('Y')
    k.left = c
    k.right = y

    val z = MorseTreeNode('Z')
    val q = MorseTreeNode('Q')
    g.left = z
    g.right = q

    val o1 = MorseTreeNode('Ö')
    val ch = MorseTreeNode('¢')
    o.left  = o1
    o.right = ch
    return parentNode
}