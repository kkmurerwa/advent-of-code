package year2023.day9

import utils.InputGetter
import java.util.*
import kotlin.math.abs

fun main() {
    val inputArr = InputGetter.getFileInput(9, 2023)

    var res = 0
    for (str in inputArr) {
        val strList = str.split(" ").map { it.toInt() }
        res += getPrevNumber(strList)
    }

    println("Res: $res")
}

fun getPrevNumber(list: List<Int>): Int {
    var prevList = list.toMutableList()
    var sumOfArr = Int.MAX_VALUE
    val stack = Stack<Int>()
    stack.add(prevList.first())

    while (sumOfArr != 0) {
        val nextList = mutableListOf<Int>()
        sumOfArr = 0
        for (i in 1..<prevList.size) {
            val diff = prevList[i] - prevList[i - 1]
            sumOfArr += abs(diff)
            nextList.add(diff)
        }

        stack.add(nextList.first())
        prevList = nextList
    }

    var last = stack.pop()
    while (stack.isNotEmpty()) {
        last = stack.pop() - last
    }

    return last
}