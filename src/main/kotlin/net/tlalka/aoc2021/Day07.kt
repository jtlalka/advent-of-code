package net.tlalka.aoc2021

import kotlin.math.absoluteValue

class Day07 {

    fun part1(input: List<String>): Int {
        val data = input.parseInput()
        val median = data.size.let { (data[it / 2] + data[(it - 1) / 2]) / 2 }

        return data.fold(0) { acc, i ->
            acc + (median - i).absoluteValue
        }
    }

    fun part2(input: List<String>): Int {
        val data = input.parseInput()

        return (data.first() until data.last()).minOf { target ->
            data.fold(0) { acc, i ->
                (target - i).absoluteValue.let { acc + it * (it + 1) / 2 }
            }
        }
    }

    private fun List<String>.parseInput(): List<Int> = single()
        .split(",")
        .map(String::toInt)
        .sorted()
}
