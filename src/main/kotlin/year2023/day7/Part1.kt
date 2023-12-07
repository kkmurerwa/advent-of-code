package year2023.day7

import utils.InputGetter

fun main() {
    val res = performInputCalculations(
        getCardTypeValue = { str -> part1GetCardTypeValue(str) },
        getValueOf = { char -> part1GetValueOf(char) }
    )

    println("Res: $res")
}

fun performInputCalculations(getCardTypeValue: (String) -> Int, getValueOf: (Char) -> Int): Int {
    val inputArr = InputGetter.getFileInput(7, 2023)

    val cards = mutableListOf<String>()
    val amounts = mutableMapOf<String, Int>()

    for (str in inputArr) {
        val tempArr = str.split(" ")
        cards.add(tempArr[0])
        amounts[tempArr[0]] = tempArr[1].toInt()
    }

    val mapOfCards = mutableMapOf<Int, MutableList<String>>()

    for (card in cards) {
        val kindValue = getCardTypeValue(card)
        val list = mapOfCards.getOrDefault(kindValue, mutableListOf())
        list.add(card)
        mapOfCards[kindValue] = list
    }



    val mapOfCardsSorted = mapOfCards.toSortedMap().map { entry ->
        entry.value.sortedWith(compareBy(
            { getValueOf(it[0]) },
            { getValueOf(it[1]) },
            { getValueOf(it[2]) },
            { getValueOf(it[3]) },
            { getValueOf(it[4]) }
        ))
    }

    var rank = 1
    var res = 0
    for (arr in mapOfCardsSorted) {
        for (str in arr) {
            res += amounts.getValue(str) * rank
            rank += 1
        }
    }

    return res
}

private fun part1GetValueOf(char: Char): Int {
    val map = mapOf(
        'A' to 13,
        'K' to 12,
        'Q' to 11,
        'J' to 10,
        'T' to 9,
        '9' to 8,
        '8' to 7,
        '7' to 6,
        '6' to 5,
        '5' to 4,
        '4' to 3,
        '3' to 2,
        '2' to 1
    )

    return map.getValue(char)
}

private fun part1GetCardTypeValue(str: String): Int {
    val map = mutableMapOf<Char, Int>()

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

            if (max == 4) CardKind.FOUR_OF_A_KIND
            else CardKind.FULL_HOUSE
        }
        3 -> {
            var pairs = 0
            for (i in mapValues) {
                if (i == 2) pairs += 1
            }

            if (pairs == 2) CardKind.TWO_PAIR
            else CardKind.THREE_OF_A_KIND
        }
        4 -> CardKind.ONE_PAIR
        else -> CardKind.HIGH_CARD
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

enum class CardKind {
    FIVE_OF_A_KIND,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
}