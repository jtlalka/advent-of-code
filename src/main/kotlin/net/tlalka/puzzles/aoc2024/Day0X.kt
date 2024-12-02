package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile

object Day0X {

    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }
}

fun main() {
    val sample = sampleFile<Day0X>().readInput()
    val puzzle = puzzleFile<Day0X>().readInput()

    with(Day0X) {
        println("--")
        println("Part 1 sample: " + part1(sample))
        println("Part 1 puzzle: " + part1(puzzle))
        println("Part 2 sample: " + part2(sample))
        println("Part 2 puzzle: " + part2(puzzle))
    }
}