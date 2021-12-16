package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.puzzleFile
import net.tlalka.aoc2021.common.readInput
import net.tlalka.aoc2021.common.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day16Test {

    private val tested = Day16()

    private val sampleInput = sampleFile<Day16>().readInput()
    private val puzzleInput = puzzleFile<Day16>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(31, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(1014, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(54L, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(1922490999789L, tested.part2(puzzleInput))
    }
}
