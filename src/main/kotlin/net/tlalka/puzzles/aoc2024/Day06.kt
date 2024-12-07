package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.hasCycle
import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.utils.Grid
import net.tlalka.puzzles.common.utils.Grid.Builder.toGrid
import net.tlalka.puzzles.common.utils.Grid.Point

class Day06 {

    fun part1(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid()
        var position = grid.indexOf(GUARD)
        var vector = Vector.UP

        while (grid.getOrNull(position) != null) {
            if (grid.getOrNull(point = position + vector) == '#') {
                vector = vector.next()
                continue
            }
            position += vector
            grid.setValue(position, MARKER_X)
        }
        return grid.count { _, value -> value == MARKER_X }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid()

        return grid.count { point, place ->
            if (place == '.') {
                findCycle(grid.copy().apply { setValue(point, element = '#') })
            } else {
                false
            }
        }
    }

    private fun findCycle(grid: Grid<Char>): Boolean {
        val trace = mutableListOf<Pair<Vector, Point>>()
        var vector = Vector.UP
        var position = grid.indexOf(GUARD)

        while (grid.getOrNull(position) != null) {
            if (grid.getOrNull(position + vector) == '#') {
                vector = vector.next()
                trace.add(vector to position)

                when (trace.hasCycle()) {
                    true -> return true
                    false -> continue
                }
            }
            position += vector
        }
        return false
    }

    private operator fun Point.plus(vector: Vector) = Point(x = x + vector.dx, y = y + vector.dy)

    private enum class Vector(val dx: Int, val dy: Int, val next: () -> Vector) {
        UP(dx = 0, dy = -1, next = { RIGHT }),
        RIGHT(dx = 1, dy = 0, next = { DOWN }),
        DOWN(dx = 0, dy = 1, next = { LEFT }),
        LEFT(dx = -1, dy = 0, next = { UP })
    }

    companion object {
        private const val GUARD = '^'
        private const val MARKER_X = 'X'
    }
}

fun main() {
    val sample = sampleFile<Day06>().readInput()
    val puzzle = puzzleFile<Day06>().readInput()

    with(Day06()) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 41
        println("Part 1 puzzle: " + part1(puzzle)) // 5329
        println("Part 2 sample: " + part2(sample)) // 6
        println("Part 2 puzzle: " + part2(puzzle)) // 2162
    }
}