package year2023.day1

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(1, 2023)
    val processedArr = processDay1Part2Input(inputArr)

    var sum = 0
    for (arr in processedArr) {
        sum += arr[0] * 10 + arr[arr.size - 1]
    }

    println("Sum: $sum")
}

fun processDay1Part2Input(rawArr: List<String>): MutableList<MutableList<Int>> {
    println("Input arr: $rawArr")

    val processedArr = mutableListOf<MutableList<Int>>()

    val filtered = rawArr.map { str ->
        str.replace("zero", "z0o")
            .replace("one", "o1e")
            .replace("two", "t2o")
            .replace("three", "t3e")
            .replace("four", "f4r")
            .replace("five", "f5e")
            .replace("six", "s6x")
            .replace("seven", "s7n")
            .replace("eight", "e8t")
            .replace("nine", "n9e")
    }

    println("Filtered: $filtered")

    for (str in filtered) {
        val temp = mutableListOf<Int>()
        for (char in str) {
            if (char.isDigit()) {
                temp.add(char.digitToInt())
            }
        }

        processedArr.add(temp)
    }

    println("Processed Arr: $processedArr")

    return processedArr
}