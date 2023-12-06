package year2023.day6

import utils.InputGetter

fun main() {
    val processed = processDay6Part1Input()

    val arr1 = processed[0]
    val arr2 = processed[1]

    var res = 1
    for (i in arr1.indices) {
        var num = 1
        var count = arr1[i]
        while (true) {
            val distance = (arr1[i] - num) * num

            if (distance <= arr2[i]) {
                count -= 2
            } else break

            num ++
        }

        res *= if(count <= 0) 1 else count - 1
    }

    println("Res: $res")
}

fun processDay6Part1Input(): MutableList<List<Int>> {
    val inputArr = InputGetter.getFileInput(6, 2023)

    val processed = mutableListOf<List<Int>>()
    inputArr.forEach { str ->
        val inputs = str.split(": ")[1].split(" ").map { it.toInt() }
        processed.add(inputs)
    }

    return processed
}