package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile

object Day02 {

    fun part1(input: List<String>): Int {
        val reports = input.map { row -> row.split(' ').map { it.toInt() } }
        val diffs = reports.map { row -> row.zipWithNext { n1, n2 -> n1 - n2 } }
        return diffs.count { it.isValid() }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { row -> row.split(' ').map { it.toInt() } }
        val diffs = reports.map { row -> row.tryToFixRow(position = -1) }
        return diffs.count { it.isNotEmpty() }
    }

    private fun List<Int>.tryToFixRow(position: Int): List<Int> {
        val filtered = filterIndexed { index, _ -> index != position }
        val diffs = filtered.zipWithNext { n1, n2 -> n1 - n2 }

        return when {
            diffs.isValid() -> filtered
            position < lastIndex -> tryToFixRow(position + 1)
            else -> emptyList()
        }
    }

    private fun List<Int>.isValid() = all { it in (1..3) } || all { it in (-3..-1) }
}

fun main() {
    val sample = sampleFile<Day02>().readInput()
    val puzzle = puzzleFile<Day02>().readInput()

    with(Day02) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 2
        println("Part 1 puzzle: " + part1(puzzle)) // 670
        println("Part 2 sample: " + part2(sample)) // 4
        println("Part 2 puzzle: " + part2(puzzle)) // 700
    }
}