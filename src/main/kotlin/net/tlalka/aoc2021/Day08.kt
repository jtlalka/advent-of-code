package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.toPair

class Day08 {

    fun part1(input: List<String>): Int = input
        .parseInput()
        .values
        .flatten()
        .count { it.length in listOf(2, 3, 4, 7) }

    /**
     * n: --0--
     *    1   2
     *    --3--
     *    4   5
     *    --6--
     */
    fun part2(input: List<String>): Int {
        val n = CharArray(7)
        var result = 0

        input.parseInput().forEach { (key, values) ->
            val lengthMap = key.associate { it.length to it.toSet() }
            val charCount = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g')
                .associateBy { char -> key.toSet().sumOf { keyChar -> keyChar.count { it == char } } }

            n[0] = (lengthMap.getValue(3) - lengthMap.getValue(2)).first()
            n[1] = charCount.getValue(6)
            n[4] = charCount.getValue(4)
            n[5] = charCount.getValue(9)
            n[2] = (lengthMap.getValue(2) - n[5]).first()
            n[3] = (lengthMap.getValue(4) - setOf(n[1], n[2], n[5])).first()
            n[6] = (lengthMap.getValue(7) - setOf(n[0], n[1], n[2], n[3], n[4], n[5])).first()

            result += values.fold("") { acc, value ->
                acc + when (value.toSet()) {
                    setOf(n[2], n[5]) -> "1"
                    setOf(n[0], n[2], n[3], n[4], n[6]) -> "2"
                    setOf(n[0], n[2], n[3], n[5], n[6]) -> "3"
                    setOf(n[1], n[2], n[3], n[5]) -> "4"
                    setOf(n[0], n[1], n[3], n[5], n[6]) -> "5"
                    setOf(n[0], n[1], n[3], n[4], n[5], n[6]) -> "6"
                    setOf(n[0], n[2], n[5]) -> "7"
                    setOf(n[0], n[1], n[2], n[3], n[4], n[5], n[6]) -> "8"
                    setOf(n[0], n[1], n[2], n[3], n[5], n[6]) -> "9"
                    setOf(n[0], n[1], n[2], n[4], n[5], n[6]) -> "0"
                    else -> throw Exception("BUM >> $value")
                }
            }.toInt()
        }
        return result
    }

    private fun List<String>.parseInput(): Map<List<String>, List<String>> = this
        .map { it.split(" | ") }
        .map { row -> row.map { it.split(" ") } }
        .associate { it.toPair() }
}
