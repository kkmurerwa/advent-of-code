package year2023.day7

import utils.InputGetter

fun main() {
    println("[2023] Day seven, part two...")

    val cardsByValue = listOf("A", "K", "Q", "T", "9", "8", "7", "6", "5", "4", "3", "2").reversed()
//    val handBidPairs = input().split("\n")
//        .map { it.trim() }
//        .map {
//            it.split(" ")[0].trim() to
//                    it.split(" ")[1].toInt()
//        }

    val rawInput = InputGetter.getFileInput(7, 2023, "input")
    val handBidPairs = rawInput.map { it.trim() }
        .map {
            it.split(" ")[0].trim() to
                    it.split(" ")[1].toInt()
        }
    fun getHandValue(hand: List<String>): Int {
        return when {
            hand.count { it == hand[0] } == 5 -> 10
            hand.any { char -> hand.count { it == char } == 4 } -> 9
            hand.any { char -> hand.count { it == char } == 3 } && hand.toSet().size == 2 -> 8
            hand.any { char -> hand.count { it == char } == 3 } -> 7
            hand.any { char -> hand.count { it == char } == 2 } && hand.toSet().size == 3 -> 6
            hand.any { char -> hand.count { it == char } == 2 } && hand.toSet().size == 4 -> 5
            else -> 0
        }
    }

    val comparator = Comparator<Pair<String, Int>> { (hand1, _), (hand2, _) ->
        val hand1Cards = hand1.split("").filter { it.isNotBlank() }.toMutableList()
        val hand2Cards = hand2.split("").filter { it.isNotBlank() }.toMutableList()
        var hand1Value = getHandValue(hand1Cards)
        var hand2Value = getHandValue(hand2Cards)

        if (hand1.contains("J")) {
            cardsByValue.forEach {
                val replacedHand1 = hand1Cards.joinToString("")
                    .replace("J", it)
                    .split("")
                    .filter { it.isNotBlank() }

                val replacedJValue = getHandValue(replacedHand1)
                if (replacedJValue > hand1Value) {
                    hand1Value = replacedJValue
                }
            }
        }

        if (hand2.contains("J")) {
            cardsByValue.forEach {
                val replacedHand2 = hand2Cards.joinToString("")
                    .replace("J", it)
                    .split("")
                    .filter { it.isNotBlank() }

                val replacedJValue = getHandValue(replacedHand2)
                if (replacedJValue > hand2Value) {
                    hand2Value = replacedJValue
                }
            }
        }

        if (hand1Value > hand2Value) {
            return@Comparator 1
        }
        if (hand1Value < hand2Value) {
            return@Comparator -1
        }

        repeat(hand1Cards.size) { idx ->
            if (cardsByValue.indexOf(hand1Cards[idx]) > cardsByValue.indexOf(hand2Cards[idx])) {
                return@Comparator 1
            }

            if (cardsByValue.indexOf(hand1Cards[idx]) < cardsByValue.indexOf(hand2Cards[idx])) {
                return@Comparator -1
            }
        }

        println("Shouldn't happen")
        0
    }

    val sortedHandBidPairs = handBidPairs.sortedWith(comparator)
    var sum = 0
    sortedHandBidPairs.forEachIndexed { index, (_, bid) ->
        sum += (index + 1) * bid
    }

    println(sum)
}