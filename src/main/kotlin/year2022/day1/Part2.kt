package year2022.day1

import utils.InputGetter

fun main() {
    val rawArr = InputGetter.getFileInput(day = 1, year = 2022)

    val inputArr = processDay1Input(rawArr)

    var max = 0
    var max2 = 0
    var max3 = 0

    for (arr in inputArr) {
        val currSum: Int = arr.reduce { acc, next -> acc + next }

        if (currSum > max) {
            max3 = max2
            max2 = max
            max = currSum
        } else if (currSum > max2) {
            max3 = max2
            max2 = currSum
        } else if (currSum > max3) {
            max3 = currSum
        }
    }

    val res = max + max2 + max3

    println("Res: $res")
}