package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {

    private val tested = Day08()

    private val sampleInput = sampleFile<Day08>().readInput()
    private val puzzleInput = puzzleFile<Day08>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(26, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(237, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(61229, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(1009098, tested.part2(puzzleInput))
    }
}
