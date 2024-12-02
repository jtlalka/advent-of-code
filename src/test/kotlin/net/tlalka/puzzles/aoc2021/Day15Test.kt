package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15Test {

    private val tested = Day15()

    private val sampleInput = sampleFile<Day15>().readInput()
    private val puzzleInput = puzzleFile<Day15>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(40, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(366, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(315, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(2829, tested.part2(puzzleInput))
    }
}
