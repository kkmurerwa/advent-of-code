package year2023.day4

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(4, 2023)

    var res = 0
    inputArr.forEach {str ->
        val withoutLabel = str.split(":")

        val cardNumsStr = withoutLabel[1].trim().split(" | ")

        val winningNumsSet = cardNumsStr[0].trim().split(" ").toSet()

        val cardNums = cardNumsStr[1].trim().split("  "," ")

        var tempScore = 0
        for (numStr in cardNums) {
            if (winningNumsSet.contains(numStr)) {
                if (tempScore == 0) tempScore = 1
                else tempScore *= 2
            }
        }

        res += tempScore
    }

    println("Res: $res")
}