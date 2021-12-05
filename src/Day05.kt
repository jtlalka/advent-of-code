import kotlin.math.absoluteValue
import kotlin.math.sign

fun main() {

    fun part1(input: List<Pair<Point, Point>>): Int = buildMap<Point, Int> {
        input.forEach { pair ->
            val p1 = pair.first
            val p2 = pair.second

            when {
                p1.x == p2.x -> (p1 lineTo p2).forEach { put(it, getOrElse(it) { 0 } + 1) }
                p1.y == p2.y -> (p1 lineTo p2).forEach { put(it, getOrElse(it) { 0 } + 1) }
            }
        }
    }.values.count { it > 1 }

    fun part2(input: List<Pair<Point, Point>>): Int = input
        .map { it.first lineTo it.second }
        .flatten()
        .groupingBy { it }
        .eachCount()
        .count { it.value > 1 }

    fun transform(input: List<String>): List<Pair<Point, Point>> = input.map { line ->
        val (p1, p2) = line.split(" -> ")
        val (x1, y1) = p1.split(",").map { it.toInt() }
        val (x2, y2) = p2.split(",").map { it.toInt() }

        Pair(Point(x1, y1), Point(x2, y2))
    }

    listOf("Day05-test", "Day05-data")
        .map(::readStringInput)
        .map(::transform)
        .onEach {
            println(">> P1: " + part1(it)) // [5] [6283]
            println(">> P2: " + part2(it)) // [12] [18864]
        }
}

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
