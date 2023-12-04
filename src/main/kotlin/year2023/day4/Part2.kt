package year2023.day4

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(4, 2023)
    val mapCount = hashMapOf<Int, Int>()
    val mapCards = hashMapOf<Int, MutableSet<Int>>()

    for (i in inputArr.indices) {
        mapCount[i + 1] = 1
    }

    inputArr.forEachIndexed {index, str ->
        val withoutLabel = str.split(":")
        val cardNumsStr = withoutLabel[1].trim().split(" | ")
        val winningNumsSet = cardNumsStr[0].trim().split(" ").toSet()
        val cardNums = cardNumsStr[1].trim().split("  "," ")

        var tempScore = 0
        for (numStr in cardNums) {
            if (winningNumsSet.contains(numStr)) {
                tempScore += 1
            }
        }

        val startPoint = index + 2
        val endPoint = index + tempScore + 1
        for (i in startPoint..endPoint) {
            val key = index + 1
            val set = mapCards.getOrDefault(key, mutableSetOf())
            set.add(i)
            mapCards[key] = set
        }
    }

    var res = 0
    for (entry in mapCount) {
        val curr = entry.key

        var cumulative = 0
        for (i in 1..<entry.key) {
            if (mapCards[i] != null && mapCards.getValue(i).contains(curr)) {
                cumulative += mapCount.getValue(i)
            }
        }
        mapCount[curr] = mapCount[curr]!! + cumulative
        res += mapCount.getValue(curr)
    }

    println("Res: $res")
}