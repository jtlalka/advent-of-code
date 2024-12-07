package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.aoc2024.Day04.Vector.LEFT_DOWN
import net.tlalka.puzzles.aoc2024.Day04.Vector.LEFT_UP
import net.tlalka.puzzles.aoc2024.Day04.Vector.RIGHT_DOWN
import net.tlalka.puzzles.aoc2024.Day04.Vector.RIGHT_UP
import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.utils.Grid.Builder.toGrid
import net.tlalka.puzzles.common.utils.Grid.Point

class Day04 {

    fun part1(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid()

        return grid.sumOf { (x, y), _ ->
            Vector.entries.count { (dx, dy) ->
                "XMAS".withIndex().all { (index, letter) ->
                    grid.getOrNull(Point(x = x + index * dx, y = y + index * dy)) == letter
                }
            }
        }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map(String::toList).toGrid()

        return grid.count { point, value ->
            value == 'A' &&
                setOf(grid.getOrNull(point + LEFT_UP), grid.getOrNull(point + RIGHT_DOWN)) == MS &&
                setOf(grid.getOrNull(point + RIGHT_UP), grid.getOrNull(point + LEFT_DOWN)) == MS
        }
    }

    private operator fun Point.plus(vector: Vector) = Point(x = x + vector.dx, y = y + vector.dy)

    private enum class Vector(val dx: Int, val dy: Int) {
        RIGHT(dx = 1, dy = 0),
        LEFT(dx = -1, dy = 0),
        UP(dx = 0, dy = -1),
        DOWN(dx = 0, dy = 1),
        RIGHT_UP(dx = 1, dy = -1),
        RIGHT_DOWN(dx = 1, dy = 1),
        LEFT_UP(dx = -1, dy = -1),
        LEFT_DOWN(dx = -1, dy = 1);

        operator fun component1() = dx
        operator fun component2() = dy
    }

    private companion object {
        private val MS = "MS".toSet()
    }
}

fun main() {
    val sample = sampleFile<Day04>().readInput()
    val puzzle = puzzleFile<Day04>().readInput()

    with(Day04()) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 18
        println("Part 1 puzzle: " + part1(puzzle)) // 2545
        println("Part 2 sample: " + part2(sample)) // 9
        println("Part 2 puzzle: " + part2(puzzle)) // 1886
    }
}