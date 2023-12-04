package year2023.day2

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(2, 2023)

    val processedMap = processDay2Part1Input(inputArr)

    var power = 0

    for (entry in processedMap) {
        val colorsMap = entry.value

        power += colorsMap["red"]!! * colorsMap["green"]!! * colorsMap["blue"]!!
    }

    println("Power: $power")
}