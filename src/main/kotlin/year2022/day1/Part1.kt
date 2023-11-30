package year2022.day1

import utils.InputGetter

fun main() {
    val rawArr = InputGetter.getFileInput(day = 1, year = 2022)

    val inputArr = processDay1Input(rawArr)

    var max = 0

    for (arr in inputArr) {
        val currSum: Int = arr.reduce { acc, next -> acc + next }

        max = max.coerceAtLeast(currSum)
    }

    println("Max: $max")
}

fun processDay1Input(rawArr: List<String>): MutableList<MutableList<Int>> {
    val processedArr = mutableListOf<MutableList<Int>>()
    var tempArr = mutableListOf<Int>()
    for (str in rawArr) {
        if (str == "") {
            processedArr.add(tempArr)
            tempArr = mutableListOf()
        } else {
            tempArr.add(str.toInt())
        }
    }

    return processedArr
}