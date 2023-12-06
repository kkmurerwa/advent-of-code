package year2023.day5

import utils.InputGetter
import kotlin.collections.HashMap

fun main() {
    val processedPair = processDay5Part2Input()

    val seeds = processedPair.first
    var min = Long.MAX_VALUE
    for (pair in seeds) {
        val end = (pair.first + pair.second) - 1
        for (seed in pair.first..end) {
            val location = walk(seed, processedPair.second)
            min = min.coerceAtMost(location)
        }
    }

    println("Min: $min")
}

fun processDay5Part2Input(): Pair<List<Pair<Long, Long>>, HashMap<String, MutableList<List<Long>>>> {
    val filtered = InputGetter.getFileInput(5, 2023).filter { s -> s.isNotEmpty() }

    val seeds: MutableList<Pair<Long, Long>> = mutableListOf()
    var key = ""
    val mapOfList: HashMap<String, MutableList<List<Long>>> = HashMap()
    filtered.forEachIndexed { index, str ->
        if (index == 0) {
            val seedSplit = str.split(": ")
            val seedList = seedSplit[1].split(" ").map { it.toLong() }
            var ind = 0
            while (ind < seedList.size - 1) {
                seeds.add(Pair(seedList[ind], seedList[ind + 1]))
                ind += 2
            }
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

    return Pair(seeds, mapOfList)
}
