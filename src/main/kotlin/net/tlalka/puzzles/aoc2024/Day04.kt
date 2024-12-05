package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.utils.Grid.Companion.toGrid

class Day04 {

    fun part1(input: List<String>): Int {
        val grid = input.map { it.toList() }.toGrid()

        return grid.sumOf { x, y, _ ->
            Vector.entries.count { (dx, dy) ->
                "XMAS".withIndex().all { (index, letter) ->
                    grid.getOrNull(x = x + index * dx, y = y + index * dy) == letter
                }
            }
        }
    }

    fun part2(input: List<String>): Int {
        val grid = input.map { it.toList() }.toGrid()

        return grid.count { x, y, value ->
            value == 'A' &&
                setOf(grid.getOrNull(x = x - 1, y = y - 1), grid.getOrNull(x = x + 1, y = y + 1)) == MS &&
                setOf(grid.getOrNull(x = x + 1, y = y - 1), grid.getOrNull(x = x - 1, y = y + 1)) == MS
        }
    }

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