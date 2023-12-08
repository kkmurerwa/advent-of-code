package year2023.day8

fun main() {
    val (_, map) = processDay8Input()

    val startingPoints = map.keys.filter { it[2] == 'A' }
    val movesTaken = mutableMapOf<String, Int>()

    startingPoints.forEach { str ->
        movesTaken[str] = navigateToEnd(str)
    }

    val res = lcmOfArrayElements(movesTaken.values.toMutableList())

    println("Res: $res")
}

fun lcmOfArrayElements(elementArray: MutableList<Int>): Long {
    var lcmOfArrayElements: Long = 1
    var divisor = 2
    while (true) {
        var counter = 0
        var divisible = false
        for (i in elementArray.indices) {
            if (elementArray[i] == 0) {
                return 0
            } else if (elementArray[i] < 0) {
                elementArray[i] = elementArray[i] * -1
            }
            if (elementArray[i] == 1) {
                counter++
            }
            if (elementArray[i] % divisor == 0) {
                divisible = true
                elementArray[i] = elementArray[i] / divisor
            }
        }
        if (divisible) {
            lcmOfArrayElements *= divisor
        } else {
            divisor++
        }
        if (counter == elementArray.size) {
            return lcmOfArrayElements
        }
    }
}