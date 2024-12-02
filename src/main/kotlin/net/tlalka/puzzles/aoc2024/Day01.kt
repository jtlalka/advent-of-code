package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.extension.toPair
import kotlin.math.abs

object Day01 {

    fun part1(input: List<String>): Int {
        val pairs = input.map { it.split(" ").toPair() }
        val list1 = pairs.map { it.first.toInt() }.sorted()
        val list2 = pairs.map { it.second.toInt() }.sorted()

        return list1.zip(list2)
            .fold(initial = 0) { acc, pair -> acc + abs(pair.first - pair.second) }
    }

    fun part2(input: List<String>): Int {
        val pairs = input.map { it.split(" ").toPair() }
        val list1 = pairs.map { it.first.toInt() }
        val list2 = pairs.map { it.second.toInt() }.groupingBy { it }.eachCount()

        return list1.sumOf { it * list2.getOrDefault(it, defaultValue = 0) }
    }
}

fun main() {
    val sample = sampleFile<Day01>().readInput()
    val puzzle = puzzleFile<Day01>().readInput()

    with(Day01) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 11
        println("Part 1 puzzle: " + part1(puzzle)) // 2375403
        println("Part 2 sample: " + part2(sample)) // 31
        println("Part 2 puzzle: " + part2(puzzle)) // 23082277
    }
}