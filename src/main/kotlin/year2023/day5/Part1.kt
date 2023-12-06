package year2023.day5

import utils.InputGetter
import kotlin.collections.HashMap

fun main() {
    val inputArr = InputGetter.getFileInput(5, 2023, "input")

    val processedPair = processDay5Part1Input(inputArr)

    val seeds = processedPair.first
    var min = Long.MAX_VALUE
    for (i in seeds) {
        val location = walk(i, processedPair.second)
        min = min.coerceAtMost(location)
    }

    println("Min: $min")
}

fun walk(seed: Long, map: HashMap<String, MutableList<List<Long>>>): Long {
    val listOfWalk = listOf(
        "seed-to-soil", "soil-to-fertilizer", "fertilizer-to-water", "water-to-light", "light-to-temperature",
        "temperature-to-humidity", "humidity-to-location"
    )

    val from = 0
    val to = 1
    val range = 2

    var curr = seed

    for (key in listOfWalk) {
        val values = map[key]!!
        for (valueList in values) {
            val end = valueList[to] + valueList[range]
            if (curr >= valueList[to] && curr < end) {
                val ratio = valueList[from] - valueList[to]
                curr += ratio
                break
            }
        }
    }

    return curr
}

fun processDay5Part1Input(inputArr: List<String>): Pair<List<Long>, HashMap<String, MutableList<List<Long>>>> {
    val filtered = inputArr.filter { s -> s.isNotEmpty() }

    var seeds: List<Long> = listOf()
    var key = ""
    val mapOfList: HashMap<String, MutableList<List<Long>>> = HashMap()
    filtered.forEachIndexed { index, str ->
        if (index == 0) {
            val seedSplit = str.split(": ")
            seeds = seedSplit[1].split(" ").map { it.toLong() }
        } else {
            if (str.contains(":")) {
                key = str.split(" ")[0]
            } else {
                val list = mapOfList.getOrDefault(key, mutableListOf())
                list.add(str.split(" ").map { it.toLong() })
                mapOfList[key] = list
            }
        }
    }


    for (entry in mapOfList) {
        println(entry)
    }
    println("\n")

    return Pair(seeds, mapOfList)
}
