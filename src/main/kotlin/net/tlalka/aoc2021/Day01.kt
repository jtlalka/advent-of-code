package net.tlalka.aoc2021

class Day01 {

    fun part1(input: List<String>): Int = input
        .map(String::toInt)
        .zipWithNext()
        .count { it.first < it.second }

    fun part2(input: List<String>): Int = input
        .asSequence()
        .map(String::toInt)
        .windowed(3)
        .map { it.sum() }
        .zipWithNext()
        .count { it.first < it.second }
}
