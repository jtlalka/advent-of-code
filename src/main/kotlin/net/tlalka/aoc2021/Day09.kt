package net.tlalka.aoc2021

class Day09 {

    fun part1(input: List<String>): Int = input
        .parseInput()
        .findMinPoints()
        .sumOf { it.value + 1 }

    fun part2(input: List<String>): Int = input
        .parseInput()
        .run {
            findMinPoints()
                .map { getBasinSizeFor(it) }
                .sorted()
                .takeLast(3)
                .reduce { a, b -> a * b }
        }

    private fun Array<IntArray>.findMinPoints(): List<Point> =
        flatMapIndexed { y, row ->
            row.foldIndexed(listOf()) { x, acc, value ->
                if (value < getNextPoints(x, y).minOf { it.value }) {
                    acc.plus(Point(value, x, y))
                } else {
                    acc
                }
            }
        }

    private fun Array<IntArray>.getBasinSizeFor(point: Point): Int {
        val register = mutableSetOf(point)
        val queue = mutableListOf(point)
        while (queue.isNotEmpty()) {
            queue.removeFirst()
                .let { getNextPoints(it.x, it.y) }
                .filterNot { it in register }
                .filter { it.value != 9 }
                .let {
                    register.addAll(it)
                    queue.addAll(it)
                }
        }
        return register.size
    }

    private fun Array<IntArray>.getNextPoints(x: Int, y: Int): List<Point> = listOfNotNull(
        getOrNull(y)?.getOrNull(x - 1)?.let { Point(it, x - 1, y) },
        getOrNull(y)?.getOrNull(x + 1)?.let { Point(it, x + 1, y) },
        getOrNull(y - 1)?.getOrNull(x)?.let { Point(it, x, y - 1) },
        getOrNull(y + 1)?.getOrNull(x)?.let { Point(it, x, y + 1) }
    )

    private fun List<String>.parseInput(): Array<IntArray> = this
        .map { row -> row.map { it.digitToInt() }.toIntArray() }
        .toTypedArray()

    private data class Point(val value: Int, val x: Int, val y: Int)
}
