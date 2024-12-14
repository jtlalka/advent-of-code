package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.orZero
import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.utils.Grid.Builder.toGrid
import net.tlalka.puzzles.common.utils.Grid.Point

object Day10 {

    fun part1(input: List<String>): Int {
        val grid = input.map { row -> row.toList().map { "$it".toInt() } }.toGrid()
        val starts = grid.findAll { _, value -> value == 0 }

        return starts.sumOf { (start, _) ->
            val incompletePaths = mutableListOf(listOf(start))
            val finalPoints = mutableSetOf<Point>()

            while (incompletePaths.isNotEmpty()) {
                val path = incompletePaths.removeFirst()
                val point = path.last()

                when (val value = grid.getOrNull(point).orZero()) {
                    9 -> finalPoints += point
                    else -> incompletePaths += point.getOptions()
                        .filter { next -> grid.getOrNull(next).orZero() == value + 1 }
                        .map { path + it }
                }
            }
            finalPoints.size
        }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { row -> row.toList().map { "$it".toInt() } }.toGrid()
        val starts = grid.findAll { _, value -> value == 0 }

        return starts.sumOf { (start, _) ->
            val incompletePaths = mutableListOf(listOf(start))
            val completePaths = mutableSetOf<List<Point>>()

            while (incompletePaths.isNotEmpty()) {
                val path = incompletePaths.removeFirst()
                val point = path.last()

                when (val value = grid.getOrNull(point).orZero()) {
                    9 -> completePaths += path
                    else -> incompletePaths += point.getOptions()
                        .filter { next -> grid.getOrNull(next).orZero() == value + 1 }
                        .map { path + it }
                }
            }
            completePaths.size
        }
    }

    private fun Point.getOptions() = listOf(
        Point(x = x + 1, y = y),
        Point(x = x - 1, y = y),
        Point(x = x, y = y + 1),
        Point(x = x, y = y - 1)
    )
}

fun main() {
    val sample = sampleFile<Day10>().readInput()
    val puzzle = puzzleFile<Day10>().readInput()

    with(Day10) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 36
        println("Part 1 puzzle: " + part1(puzzle)) // 624
        println("Part 2 sample: " + part2(sample)) // 81
        println("Part 2 puzzle: " + part2(puzzle)) // 1483
    }
}