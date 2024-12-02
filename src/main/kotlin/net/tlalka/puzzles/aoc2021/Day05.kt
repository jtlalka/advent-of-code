package net.tlalka.puzzles.aoc2021

import kotlin.math.absoluteValue
import kotlin.math.sign

class Day05 {

    fun part1(input: List<String>): Int = input
        .unifyInput()
        .filter { (p1, p2) -> p1.x == p2.x || p1.y == p2.y }
        .map { it.first lineTo it.second }
        .countDuplicatePoints()

    fun part2(input: List<String>): Int = input
        .unifyInput()
        .map { it.first lineTo it.second }
        .countDuplicatePoints()

    private fun List<String>.unifyInput(): List<Pair<Point, Point>> = map { line ->
        val (p1, p2) = line.split(" -> ")
        val (x1, y1) = p1.split(",").map { it.toInt() }
        val (x2, y2) = p2.split(",").map { it.toInt() }

        Pair(Point(x1, y1), Point(x2, y2))
    }

    private fun List<List<Point>>.countDuplicatePoints() = this
        .flatten()
        .groupingBy { it }
        .eachCount()
        .count { it.value > 1 }

    private data class Point(val x: Int, val y: Int) {

        infix fun lineTo(point: Point): List<Point> {
            val vectorX = (point.x - x).absoluteValue
            val vectorY = (point.y - y).absoluteValue
            val deltaX = (point.x - x).sign
            val deltaY = (point.y - y).sign

            return (1..maxOf(vectorX, vectorY)).scan(this) { last, _ ->
                Point(last.x + deltaX, last.y + deltaY)
            }
        }
    }
}
