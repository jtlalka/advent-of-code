package net.tlalka.aoc2021

class Day14 {

    fun part1(input: List<String>): Long = input
        .iterate(10)
        .let { it.last() - it.first() }

    fun part2(input: List<String>): Long = input
        .iterate(40)
        .let { it.last() - it.first() }

    private fun List<String>.iterate(iterations: Int): List<Long> {
        var formula: Map<String, Long> = parseFormula(this)
        val rules: Map<String, String> = parseRules(this)
        val lastChar = first().last()

        repeat(iterations) {
            formula = formula.react(rules)
        }

        return formula
            .map { it.key.first() to it.value }
            .groupBy({ it.first }, { it.second })
            .map { it.value.sum() + if (it.key == lastChar) 1 else 0 }
            .sorted()
    }

    private fun parseFormula(input: List<String>): Map<String, Long> = input
        .first()
        .windowed(2)
        .groupingBy { it }
        .eachCount()
        .mapValues { it.value.toLong() }

    private fun parseRules(input: List<String>): Map<String, String> = input
        .drop(1)
        .map { it.split(" -> ") }
        .associate { it[0] to it[1] }

    private fun Map<String, Long>.react(rules: Map<String, String>): Map<String, Long> = buildMap {
        this@react.forEach { (pair, count) ->
            val center = rules.getValue(pair)
            val first = pair.first()
            val last = pair.last()

            (first + center).let { put(it, getOrDefault(it, 0L) + count) }
            (center + last).let { put(it, getOrDefault(it, 0L) + count) }
        }
    }
}
