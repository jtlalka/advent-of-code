package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.toPair

class Day13 {

    fun part1(input: List<String>): Int {
        val (folds, points) = input.parseInput()

        return points
            .map { folds.first().transform(it) }
            .distinct()
            .count()
    }

    fun part2(input: List<String>): String {
        val (folds, points) = input.parseInput()

        val paper = folds.fold(points) { acc, fold -> fold.transform(acc) }
        val maxX = paper.maxOf { it.x }
        val maxY = paper.maxOf { it.y }

        return buildString {
            (0..maxY).forEach { y ->
                (0..maxX).forEach { x ->
                    append(if (paper.contains(Point(x, y))) '#' else ' ')
                }
                appendLine()
            }
        }
    }

    private fun List<String>.parseInput(): Pair<List<Fold>, Set<Point>> {
        val (foldsInput, pointsInput) = this.partition { it.startsWith(FOLD_PREFIX) }

        val folds = foldsInput
            .map { it.removePrefix(FOLD_PREFIX).split("=").toPair() }
            .map { Fold(it.first, it.second.toInt()) }

        val points = pointsInput
            .map { it.split(",").toPair() }
            .map { Point(it.first.toInt(), it.second.toInt()) }
            .toSet()

        return Pair(folds, points)
    }

    private data class Point(val x: Int, val y: Int)

    private data class Fold(val type: String, val value: Int) {

        fun transform(points: Set<Point>): Set<Point> = points.map(::transform).toSet()

        fun transform(point: Point): Point = when (type) {
            "x" -> foldX(point)
            "y" -> foldY(point)
            else -> error("BUM >> $type")
        }

        private fun foldX(input: Point): Point = when (input.x > value) {
            true -> Point(2 * value - input.x, input.y)
            false -> input
        }

        private fun foldY(input: Point): Point = when (input.y > value) {
            true -> Point(input.x, 2 * value - input.y)
            false -> input
        }
    }

    companion object {
        private const val FOLD_PREFIX = "fold along "
    }
}
