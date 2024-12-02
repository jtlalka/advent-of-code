package net.tlalka.puzzles.aoc2021

class Day02 {

    fun part1(input: List<String>): Int {
        var horizontal = 0
        var depth = 0

        input.transform().forEach { (type, value) ->
            when (type) {
                "forward" -> horizontal += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }
        return horizontal * depth
    }

    fun part2(input: List<String>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        input.transform().forEach { (type, value) ->
            when (type) {
                "forward" -> {
                    horizontal += value
                    depth += value * aim
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        return horizontal * depth
    }

    private fun List<String>.transform(): List<Pair<String, Int>> = this
        .map { it.split(" ", limit = 2) }
        .map { (type, value) -> Pair(type, value.toInt()) }
}
