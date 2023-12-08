package year2023.day8

import utils.InputGetter

fun main() {
    val res = navigateToEnd("AAA")

    println("Res: $res")
}

fun processDay8Input(): Pair<String, MutableMap<String, Pair<String, String>>> {
    val inputArr = InputGetter.getFileInput(8, 2023, "input")
    var instructions = ""

    val map = mutableMapOf<String, Pair<String, String>>()

    inputArr.forEachIndexed { index, str ->
        if (index == 0) {
            instructions = str
        } else if (str.isNotEmpty()) {
            val tempArr = str.split(" = ")
            val options = tempArr[1].replace("(", "")
                .replace(")","").split(",").map { it.trim() }

            map[tempArr[0]] = Pair(options[0], options[1])
        }
    }

    return Pair(instructions, map)
}

fun navigateToEnd(start: String): Int {
    var curr = start
    val (instructions, map) = processDay8Input()
    val endingPoints = map.keys.filter { it[2] == 'Z' }.toSet()

    var reachedEnd = false
    var res = 0
    while (!reachedEnd) {
        for (char in instructions) {
            if (endingPoints.contains(curr)) {
                reachedEnd = true
                break
            }

            val pair =  map.getValue(curr)
            curr = if (char == 'L') {
                pair.first
            } else {
                pair.second
            }

            res += 1
        }
    }

    return res
}
