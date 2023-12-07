package year2023.day7

fun main() {
    val res = performInputCalculations(
        getCardTypeValue = { str -> part2GetCardTypeValue(str) },
        getValueOf = { char -> part2GetValueOf(char) }
    )

    println("Res: $res")
}

private fun part2GetValueOf(char: Char): Int {
    val map = mapOf(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
        'T' to 10,
        '9' to 9,
        '8' to 8,
        '7' to 7,
        '6' to 6,
        '5' to 5,
        '4' to 4,
        '3' to 3,
        '2' to 2,
        'J' to 1,
    )

    return map.getValue(char)
}

private fun part2GetCardTypeValue(str: String): Int {
    val map = mutableMapOf<Char, Int>()
    val jCount = str.filter { it == 'J' }.length

    for (char in str) {
        map[char] = map.getOrDefault(char, 0) + 1
    }

    val mapValues = map.values
    val cardKind: CardKind = when(mapValues.size) {
        1 -> CardKind.FIVE_OF_A_KIND
        2 -> {
            var max = 0
            for (i in mapValues) {
                max = max.coerceAtLeast(i)
            }

            if (jCount >= 1) CardKind.FIVE_OF_A_KIND
            else if (max == 4) CardKind.FOUR_OF_A_KIND
            else CardKind.FULL_HOUSE
        }
        3 -> {
            var max = 0
            for (i in mapValues) {
                max = max.coerceAtLeast(i)
            }

            if (max == 3) {
                when (jCount) {
                    0 -> CardKind.THREE_OF_A_KIND
                    else -> CardKind.FOUR_OF_A_KIND
                }
            } else {
                when (jCount) {
                    2 -> CardKind.FOUR_OF_A_KIND
                    1 -> CardKind.FULL_HOUSE
                    else -> CardKind.TWO_PAIR
                }
            }
        }
        4 -> {
            if (jCount >= 1) CardKind.THREE_OF_A_KIND
            else CardKind.ONE_PAIR
        }
        else -> {
            if (jCount >= 1) CardKind.ONE_PAIR
            else CardKind.HIGH_CARD
        }
    }

    return when(cardKind) {
        CardKind.FIVE_OF_A_KIND -> 7
        CardKind.FOUR_OF_A_KIND -> 6
        CardKind.FULL_HOUSE -> 5
        CardKind.THREE_OF_A_KIND -> 4
        CardKind.TWO_PAIR -> 3
        CardKind.ONE_PAIR -> 2
        CardKind.HIGH_CARD -> 1
    }
}