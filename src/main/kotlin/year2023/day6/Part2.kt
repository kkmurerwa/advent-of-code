package year2023.day6

import utils.InputGetter

fun main() {
    val processed = processDay6Part2Input()

    val arr1 = processed[0]
    val arr2 = processed[1]

    var num = 1
    var res = arr1
    while (true) {
        val distance = (arr1 - num) * num

        if (distance <= arr2) {
            res -= 2
        } else break

        num ++
    }

    println("Res: ${res - 1}")
}

fun processDay6Part2Input(): MutableList<Long> {
    val inputArr = InputGetter.getFileInput(6, 2023, "input")

    val processed = mutableListOf<Long>()
    inputArr.forEach { str ->
        val stringBuilder = StringBuilder()
        str.split(": ")[1].split(" ").forEach {
            stringBuilder.append(it)
        }
        processed.add(stringBuilder.toString().toLong())
    }

    return processed
}