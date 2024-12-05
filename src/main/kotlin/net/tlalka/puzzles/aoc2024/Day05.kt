package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile

class Day05 {

    fun part1(input: List<String>): Int {
        val comparator = RulesComparator(input.toRules())

        return input.toLines()
            .filter { it == it.sortedWith(comparator) }
            .sumOf { it[it.size / 2] }
    }

    fun part2(input: List<String>): Int {
        val comparator = RulesComparator(input.toRules())

        return input.toLines()
            .filter { it != it.sortedWith(comparator) }
            .map { it.sortedWith(comparator) }
            .sumOf { it[it.size / 2] }
    }

    private fun List<String>.toRules(): Set<String> =
        filter { it.contains('|') }.toSet()

    private fun List<String>.toLines(): List<List<Int>> =
        filter { it.contains(',') }
            .map { it.split(',').map(String::toInt) }

    class RulesComparator(private val rules: Set<String>) : Comparator<Int> {
        override fun compare(a: Int, b: Int): Int = when {
            "$a|$b" in rules -> -1
            "$b|$a" in rules -> 1
            else -> 0
        }
    }
}

fun main() {
    val sample = sampleFile<Day05>().readInput()
    val puzzle = puzzleFile<Day05>().readInput()

    with(Day05()) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 143
        println("Part 1 puzzle: " + part1(puzzle)) // 4766
        println("Part 2 sample: " + part2(sample)) // 123
        println("Part 2 puzzle: " + part2(puzzle)) // 6257
    }
}