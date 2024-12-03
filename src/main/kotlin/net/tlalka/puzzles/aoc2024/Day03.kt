package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile

object Day03 {

    fun part1(input: List<String>): Int {
        val pattern = """mul\((\d+),(\d+)\)""".toRegex()
        val matches = pattern.findAll(input.joinToString(separator = ""))

        return matches.sumOf { match ->
            match.groupValues[1].toInt() * match.groupValues[2].toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val pattern = """(mul\((\d+),(\d+)\))|do(n't)?\(\)""".toRegex()
        val matches = pattern.findAll(input.joinToString(separator = ""))
        var calculate = true

        return matches.sumOf { match ->
            when(match.value) {
                "do()" -> calculate = true
                "don't()" -> calculate = false
            }
            if (calculate && "mul" in match.value) {
                match.groupValues[2].toInt() * match.groupValues[3].toInt()
            } else {
                0
            }
        }
    }
}

fun main() {
    val sample = sampleFile<Day03>().readInput()
    val puzzle = puzzleFile<Day03>().readInput()

    with(Day03) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 161
        println("Part 1 puzzle: " + part1(puzzle)) // 162813399
        println("Part 2 sample: " + part2(sample)) // 48
        println("Part 2 puzzle: " + part2(puzzle)) // 53783319
    }
}