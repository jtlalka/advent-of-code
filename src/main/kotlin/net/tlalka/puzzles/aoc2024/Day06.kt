package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.hasCycle
import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.utils.Grid
import net.tlalka.puzzles.common.utils.Grid.Companion.toGrid
import net.tlalka.puzzles.common.utils.Grid.Point

class Day06 {

    fun part1(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid()
        var vector = Vector.UP
        var (x, y) = grid.indexOf(GUARD)

        while (grid.getOrNull(x, y) != null) {
            if (grid.getOrNull(x = x + vector.x, y = y + vector.y) == '#') {
                vector = vector.next()
                continue
            }
            x += vector.x
            y += vector.y
            grid.setValue(x, y, MARKER_X)
        }
        return grid.count { _, _, value -> value == MARKER_X }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid()

        return grid.count { x, y, place ->
            if (place == '.') {
                grid.getGrid(x = 0, y = 0, dimension = grid.dimension)
                    .apply { setValue(x = x, y = y, element = '#') }
                    .run { hasCycle(this) }
            } else {
                false
            }
        }
    }

    private fun hasCycle(grid: Grid<Char>): Boolean {
        val trace = mutableListOf<Pair<Vector, Point>>()
        var (x, y) = grid.indexOf(GUARD)
        var vector = Vector.UP

        while (grid.getOrNull(x, y) != null) {
            if (grid.getOrNull(x = x + vector.x, y = y + vector.y) == '#') {
                vector = vector.next()
                trace.add(vector to Point(x, y))

                if (trace.hasCycle()) {
                    return true
                } else {
                    continue
                }
            }
            x += vector.x
            y += vector.y
        }
        return false
    }

    private enum class Vector(val x: Int, val y: Int, val next: () -> Vector) {
        UP(x = 0, y = -1, next = { RIGHT }),
        RIGHT(x = 1, y = 0, next = { DOWN }),
        DOWN(x = 0, y = 1, next = { LEFT }),
        LEFT(x = -1, y = 0, next = { UP })
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