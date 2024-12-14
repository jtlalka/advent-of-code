package net.tlalka.puzzles.aoc2024

import net.tlalka.puzzles.common.extension.orZero
import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile

class Day09 {

    fun part1(input: List<String>): Long {
        val disk = input.parseInput()
        var startPointer = 0
        var endPointer = disk.lastIndex

        while (startPointer < endPointer) {
            if (disk[endPointer] != null) {
                for (newPointer in startPointer until endPointer) {
                    if (disk[newPointer] == null) {
                        disk[newPointer] = disk[endPointer]
                        disk[endPointer] = null
                        startPointer = newPointer
                        break
                    }
                }
            }
            endPointer--
        }
        return disk.checksum()
    }

    fun part2(input: List<String>): Long {
        val disk = input.parseInput()
        var endPointer = disk.lastIndex

        while (endPointer > 0) {
            disk[endPointer]?.let { value ->
                val fileIndex = disk.indexOfFirst { it == value }
                val fileSize = disk.count { it == value }
                val emptyIndex = disk.indexOf(List(fileSize) { null })

                if (emptyIndex != -1 && emptyIndex < fileIndex) {
                    repeat(fileSize) {
                        disk[emptyIndex + it] = value
                        disk[fileIndex + it] = null
                    }
                }
            }
            endPointer--
        }
        return disk.checksum()
    }

    private fun List<String>.parseInput() =
        first()
            .map { "$it".toInt() }
            .chunked(size = 2)
            .flatMapIndexed { index, data ->
                List(data.first()) { _ -> index } + List(data.getOrNull(1).orZero()) { _ -> null }
            }
            .toTypedArray()

    private fun <T> Array<T>.indexOf(sublist: List<T>): Int {
        loop@ for (i in 0..(lastIndex - sublist.lastIndex)) {
            for (j in 0..sublist.lastIndex) {
                if (this[i + j] != sublist[j]) continue@loop
            }
            return i
        }
        return -1
    }

    private fun Array<Int?>.checksum() =
        withIndex().sumOf { (index, value) -> index.toLong() * value.orZero() }
}

fun main() {
    val sample = sampleFile<Day09>().readInput()
    val puzzle = puzzleFile<Day09>().readInput()

    with(Day09()) {
        println("--")
        println("Part 1 sample: " + part1(sample)) // 1928
        println("Part 1 puzzle: " + part1(puzzle)) // 6279058075753
        println("Part 2 sample: " + part2(sample)) // 2858
        println("Part 2 puzzle: " + part2(puzzle)) // 6301361958738
    }
}