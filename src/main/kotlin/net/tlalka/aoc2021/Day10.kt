package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.median

class Day10 {

    fun part1(input: List<String>): Long = input
        .map(::parseLine)
        .mapNotNull { it.first }
        .fold(0) { acc, char -> acc + score1.getValue(char) }

    fun part2(input: List<String>): Long = input
        .map(::parseLine)
        .mapNotNull { it.second }
        .map { it.fold(0L) { acc, char -> acc * 5L + score2.getValue(char) } }
        .sorted()
        .median()

    private fun parseLine(line: String): Pair<Char?, List<Char>?> {
        val stack = mutableListOf<Char>()

        for (char in line) {
            if (command.containsKey(char)) {
                stack.add(command.getValue(char))
            } else {
                stack.removeLastOrNull().run {
                    if (char != this) {
                        return Pair(char, null)
                    }
                }
            }
        }
        return Pair(null, stack.reversed())
    }

    companion object {
        private val command: Map<Char, Char> = mapOf('(' to ')', '[' to ']', '{' to '}', '<' to '>')

        private val score1: Map<Char, Int> = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
        private val score2: Map<Char, Int> = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
    }
}
