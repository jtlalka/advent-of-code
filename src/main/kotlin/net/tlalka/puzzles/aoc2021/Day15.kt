package net.tlalka.puzzles.aoc2021

class Day15 {

    fun part1(input: List<String>): Int {
        val points = input.parseInput()

        return dijkstraShortestPath(
            graph = points.mapValues { Vertex(it.value) },
            start = points.keys.first(),
            end = points.keys.last()
        )
    }

    fun part2(input: List<String>): Int {
        val inputX = input.first().length
        val inputY = input.size
        val points = input.parseInput().extendInput(inputX, inputY, 5)

        return dijkstraShortestPath(
            graph = points.mapValues { Vertex(it.value) },
            start = Point(0, 0),
            end = Point(inputX * 5 - 1, inputY * 5 - 1)
        )
    }

    private fun List<String>.parseInput(): Map<Point, Int> = this
        .map { row -> row.map { it.digitToInt() } }
        .mapIndexed { y, row -> row.mapIndexed { x, n -> Point(x, y) to n } }
        .flatten()
        .toMap()

    private fun Map<Point, Int>.extendInput(inputX: Int, inputY: Int, vector: Int): Map<Point, Int> {
        val input = this

        return buildMap {
            putAll(input)

            for (y in 0 until inputY * vector) {
                for (x in 0 until inputX * vector) {
                    if (Point(x, y) !in input) {
                        val basePoint = input.getValue(Point(x % inputX, y % inputX))
                        val newValue = (x / inputX) + (y / inputY) - 1
                        put(Point(x, y), (basePoint + newValue) % 9 + 1)
                    }
                }
            }
        }
    }

    private fun dijkstraShortestPath(graph: Map<Point, Vertex>, start: Point, end: Point): Int {
        val visited = mutableSetOf<Point>()
        val unvisited = mutableMapOf(start to graph.getValue(start).apply { distance = 0 })

        while (unvisited.isNotEmpty()) {
            val (currentPoint, currentVertex) = unvisited.minByOrNull { it.value.distance } ?: error("BUM!")
            unvisited.remove(currentPoint)
            visited.add(currentPoint)

            currentPoint.getConnectedPoints()
                .filter { it in graph && it !in visited }
                .map { it to graph.getValue(it) }
                .sortedBy { it.second.distance }
                .forEach { (point, vertex) ->
                    val currentDistance = currentVertex.distance + vertex.value

                    if (currentDistance < vertex.distance) {
                        vertex.distance = currentDistance
                        vertex.prev = currentPoint
                    }
                    unvisited[point] = vertex
                }
        }

        return graph.getValue(end).distance
    }

    private data class Vertex(val value: Int, var distance: Int = Int.MAX_VALUE, var prev: Point? = null)

    private data class Point(val x: Int, val y: Int) {
        fun getConnectedPoints() = listOf(
            Point(x, y + 1),
            Point(x, y - 1),
            Point(x + 1, y),
            Point(x - 1, y)
        )
    }
}
