package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.median
import kotlin.math.absoluteValue

class Day07 {

    fun part1(input: List<String>): Int {
        val data = input.parseInput()
        val median = data.median()

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
