package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.utils.Grid
import net.tlalka.puzzles.common.utils.Grid.Builder.toGrid
import net.tlalka.puzzles.common.utils.Grid.Point
import net.tlalka.puzzles.common.utils.Grid.Size

class Day08 {

    private val part1Operations: List<(Point, Point) -> Point> = listOf(
        { a, b -> a + a - b },
        { a, b -> b + b - a }
    )

    private val part2Operations: List<(Size, Point, Point) -> Set<Point>> = listOf(
        { size, a, b -> generateSequence(a) { it + a - b }.takeWhile { size.contains(it) }.toSet() },
        { size, a, b -> generateSequence(b) { it + b - a }.takeWhile { size.contains(it) }.toSet() }
    )

    fun part1(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid().apply {
            markedAntiNodes { (first, second) ->
                part1Operations.map { it.invoke(first, second) }
            }
        }
        return grid.count { _, value -> value == '#' }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid().apply {
            markedAntiNodes { (first, second) ->
                part2Operations.flatMap { it.invoke(size(), first, second) }
            }
        }
        return grid.count { _, value -> value == '#' }
    }

    private fun Grid<Char>.markedAntiNodes(operation: (Pair<Point, Point>) -> List<Point>) {
        findAll { _, value -> value != '.' }
            .groupBy(keySelector = { it.second }, valueTransform = { it.first })
            .flatMap { (_, points) -> points.allUniquePairs() }
            .flatMap { pair -> operation.invoke(pair) }
            .forEach { setValue(it, '#') }
    }

    private fun List<Point>.allUniquePairs(): List<Pair<Point, Point>> =
        flatMapIndexed { i, point1 -> drop(i + 1).map { point2 -> point1 to point2 } }
}

fun main() {
    val sample = sampleFile<Day08>().readInput()
    val puzzle = puzzleFile<Day08>().readInput()

    with(Day08()) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 14
        println("Part 1 puzzle: " + part1(puzzle)) // 311
        println("Part 2 sample: " + part2(sample)) // 34
        println("Part 2 puzzle: " + part2(puzzle)) // 1115
    }
}