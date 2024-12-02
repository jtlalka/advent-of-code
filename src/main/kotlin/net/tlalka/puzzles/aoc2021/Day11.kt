package net.tlalka.puzzles.aoc2021

class Day11 {

    fun part1(input: List<String>): Int = input.parsInput().run {
        countFlashes(toMutableMap()).take(100).sum()
    }

    fun part2(input: List<String>): Int = input.parsInput().run {
        countFlashes(toMutableMap()).indexOfFirst { it == this.size } + 1
    }

    private fun countFlashes(cave: MutableMap<Point, Int>): Sequence<Int> = sequence {
        while (true) {
            var updateSize = cave.onEach { (point, value) -> cave[point] = value + 1 }.count()

            while (updateSize > 0) {
                cave.filterValues { it > 9 }
                    .keys
                    .onEach { cave[it] = 0 }
                    .flatMap { it.getGridPoints(1) }
                    .filter { it in cave && cave[it] != 0 }
                    .onEach { cave[it] = cave.getValue(it) + 1 }
                    .also { updateSize = it.count() }
            }
            yield(cave.count { it.value == 0 })
        }
    }

    private fun List<String>.parsInput(): Map<Point, Int> = this
        .flatMapIndexed { y, row -> row.mapIndexed { x, n -> Point(x, y) to n.digitToInt() } }
        .toMap()

    private data class Point(val x: Int, val y: Int) {

        fun getGridPoints(radius: Int = 1): List<Point> =
            (-radius..radius).flatMap { dY ->
                (-radius..radius).mapNotNull { dX ->
                    Point(x + dX, y + dY)
                }
            }
    }
}
