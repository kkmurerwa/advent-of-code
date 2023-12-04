package year2023.day1

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(1, 2023)
    val processedArr = processDay1Part1Input(inputArr)

    var sum = 0
    for (arr in processedArr) {
        sum += arr[0] * 10 + arr[arr.size - 1]
    }

    println("Sum: $sum")
}

fun processDay1Part1Input(rawArr: List<String>): MutableList<MutableList<Int>> {
    val processedArr = mutableListOf<MutableList<Int>>()

    for (str in rawArr) {
        val temp = mutableListOf<Int>()
        for (char in str) {
            if (char.isDigit()) {
                temp.add(char.digitToInt())
            }
        }

        processedArr.add(temp)
    }

    return processedArr
}