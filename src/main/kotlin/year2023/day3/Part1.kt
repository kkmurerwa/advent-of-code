package year2023.day3

import utils.InputGetter

fun main() {
    val inputArr = InputGetter.getFileInput(3, 2023)

    val matrix  = processDay3Part1Input(inputArr)

    val combinations = listOf(
        listOf(0, -1), listOf(0, 1), // Horizontal
        listOf(1, 0), listOf(-1, 0), // Vertical
        listOf(1, 1), listOf(1, -1), listOf(-1, -1), listOf(-1, 1) // Diagonal
    )

    var res = 0

    var num = 0
    var isAdjacent = false
    matrix.forEachIndexed { x, arr ->
        arr.forEachIndexed { y, char ->
            if (char.isDigit()) {
                num = num * 10 + char.digitToInt()

                // Check if neighbor is special char
                for (check in combinations) {
                    val xCheck = x + check[0]
                    val yCheck = y + check[1]

                    if (matrix.getOrNull(xCheck) != null && matrix.getOrNull(yCheck) != null) {
                        val curr = matrix[xCheck][yCheck]
                        if (!curr.isDigit() && curr != ' ') {
                            isAdjacent = true
                            break
                        }
                    }
                }
            } else {
                if (isAdjacent) {
                    res += num
                }

                num = 0
                isAdjacent = false
            }
        }
    }

    println("Res: $res")
}

fun processDay3Part1Input(inputArr: List<String>): MutableList<MutableList<Char>> {
    val matrix = mutableListOf<MutableList<Char>>()

    for (str in inputArr) {
        val temp = mutableListOf<Char>()

        for (char in str) {
            temp.add(if (char == '.') ' ' else char)
        }

        temp.add(' ')
        matrix.add(temp)
    }

    return matrix
}