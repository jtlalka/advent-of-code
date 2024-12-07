package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import net.tlalka.puzzles.common.extension.toPair

class Day07 {

    private val part1Operations: List<(Long, Long) -> Long> = listOf(
        { a, b -> a + b },
        { a, b -> a * b }
    )

    private val part2Operations: List<(Long, Long) -> Long> = listOf(
        { a, b -> a + b },
        { a, b -> a * b },
        { a, b -> "$a$b".toLong() }
    )

    fun part1(input: List<String>): Long =
        input.toCalculationModel()
            .filter { it.target in calculate(model = it, operators = part1Operations) }
            .sumOf { it.target }

    fun part2(input: List<String>): Long =
        input.toCalculationModel()
            .filter { it.target in calculate(model = it, operators = part2Operations) }
            .sumOf { it.target }

    private fun List<String>.toCalculationModel(): Sequence<CalculationModel> =
        asSequence()
            .map { line -> line.split(": ").toPair() }
            .map { it.first.toLong() to it.second.split(" ").map(String::toLong) }
            .map { CalculationModel(target = it.first, result = 0, numbers = it.second) }

    private fun calculate(
        model: CalculationModel,
        operators: List<(Long, Long) -> Long>,
    ): List<Long> = with(model) {
        if (numbers.isEmpty() || result > target) return listOf(result)

        return operators.flatMap { operator ->
            calculate(
                model = CalculationModel(
                    target = target,
                    result = operator(result, numbers.first()),
                    numbers = numbers.drop(1)
                ),
                operators = operators
            )
        }
    }

    private data class CalculationModel(
        val target: Long,
        val result: Long,
        val numbers: List<Long>,
    )
}

fun main() {
    val sample = sampleFile<Day07>().readInput()
    val puzzle = puzzleFile<Day07>().readInput()

    with(Day07()) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 3749
        println("Part 1 puzzle: " + part1(puzzle)) // 1289579105366
        println("Part 2 sample: " + part2(sample)) // 11387
        println("Part 2 puzzle: " + part2(puzzle)) // 92148721834692
    }
}