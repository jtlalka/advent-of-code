package net.tlalka.puzzles.aoc2021

class Day06 {

    fun part1(input: List<String>): Long = simulateFor(days = 80, input.parseInput())

    fun part2(input: List<String>): Long = simulateFor(days = 256, input.parseInput())

    private fun simulateFor(days: Int, population: LongArray): Long {
        repeat(days) {
            val newBorn = population.first()

            population.copyInto(population, startIndex = 1)
            population[6] += newBorn
            population[8] = newBorn
        }
        return population.sum()
    }

    private fun List<String>.parseInput(): LongArray = single()
        .split(",")
        .map(String::toInt)
        .fold(LongArray(9)) { acc, n -> acc.apply { this[n] += 1L } }
}
