package year2023.day2

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(2, 2023)

    val processedMap = processDay2Part1Input(inputArr)

    val red = 12
    val green = 13
    val blue = 14
    var count = 0

    for (entry in processedMap) {
        val colorsMap = entry.value

        if (colorsMap["red"]!! <= red && colorsMap["green"]!! <= green && colorsMap["blue"]!! <= blue) {
            count += entry.key.toInt()
        }
    }

    println("Count: $count")
}

fun processDay2Part1Input(rawArr: List<String>): MutableMap<String, MutableMap<String, Int>> {
    val finalMap = mutableMapOf<String, MutableMap<String, Int>>()

    rawArr.forEach {rawStr ->
        val division = rawStr.split(":")

        val gameInfo = division[0].split(" ")

        val picks = division[1].split(";")

        val map = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)

        for (str in picks) {
            val sections = str.split(",")

            for (section in sections) {
                val pair = section.trim().split(" ")

                val key = pair[1]
                map[key] = map[key]!!.coerceAtLeast(pair[0].toInt())
            }
        }

        finalMap[gameInfo[1]] = map
    }

    return finalMap
}
