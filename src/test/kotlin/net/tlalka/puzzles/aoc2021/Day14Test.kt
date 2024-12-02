package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {

    private val tested = Day14()

    private val sampleInput = sampleFile<Day14>().readInput()
    private val puzzleInput = puzzleFile<Day14>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(1588, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(3213, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(2188189693529, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(3711743744429, tested.part2(puzzleInput))
    }
}
