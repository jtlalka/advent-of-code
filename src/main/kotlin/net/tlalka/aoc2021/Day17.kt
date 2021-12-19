package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.toPair
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day17 {

    fun part1(input: List<String>): Int {
        val (inputX, inputY) = input.single().parseInput()
        val rangeX = inputX.parseIntRange("x=")
        val rangeY = inputY.parseIntRange("y=")

        return (0..rangeX.last).maxOf { x ->
            (rangeY.first..rangeY.first.absoluteValue)
                .map { y -> generate(Point(x, y)) }
                .map { res -> res.takeWhile { !it.outOfRange(rangeX, rangeY) } }
                .maxOf { res -> if (res.any { it.inRange(rangeX, rangeY) }) res.maxOf { it.y } else 0 }
        }
    }

    fun part2(input: List<String>): Int {
        val (inputX, inputY) = input.single().parseInput()
        val rangeX = inputX.parseIntRange("x=")
        val rangeY = inputY.parseIntRange("y=")

        return (0..rangeX.last).sumOf { x ->
            (rangeY.first..rangeY.first.absoluteValue)
                .map { y -> generate(Point(x, y)) }
                .map { res -> res.first { it.outOfRange(rangeX, rangeY) || it.inRange(rangeX, rangeY) } }
                .count { res ->
                    res.inRange(rangeX, rangeY)
                }
        }
    }

    private fun generate(initial: Point): Sequence<Point> = sequence {
        var position = Point(0, 0)
        var velocity = initial
        while (true) {
            position += velocity
            velocity = velocity.updateVelocity()

            yield(position)
        }
    }

    private fun String.parseInput(): Pair<String, String> = this
        .removePrefix("target area: ")
        .split(", ")
        .toPair()

    private fun String.parseIntRange(prefix: String): IntRange = this
        .removePrefix(prefix)
        .split("..")
        .map { it.toInt() }
        .let { (n, m) -> n..m }

    private data class Point(val x: Int, val y: Int) {

        operator fun plus(target: Point): Point = Point(
            x = x + target.x,
            y = y + target.y
        )

        fun updateVelocity(): Point = Point(
            x = x + x.sign * -1,
            y = y - 1
        )

        fun inRange(rangeX: IntRange, rangeY: IntRange): Boolean = x in rangeX && y in rangeY

        fun outOfRange(rangeX: IntRange, rangeY: IntRange): Boolean = x > rangeX.last || y < rangeY.first
    }
}
