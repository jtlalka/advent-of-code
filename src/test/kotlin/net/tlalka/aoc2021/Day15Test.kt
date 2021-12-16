package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.puzzleFile
import net.tlalka.aoc2021.common.readInput
import net.tlalka.aoc2021.common.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15Test {

    private val tested = Day15()

    private val sampleInput = sampleFile<Day15>().readInput()
    private val puzzleInput = puzzleFile<Day15>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(0L, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(0L, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(0L, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(0L, tested.part2(puzzleInput))
    }
}
