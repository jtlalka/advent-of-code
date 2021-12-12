package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.puzzleFile
import net.tlalka.aoc2021.common.readInput
import net.tlalka.aoc2021.common.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    private val tested = Day12()

    private val sampleInput = sampleFile<Day12>().readInput()
    private val puzzleInput = puzzleFile<Day12>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(10, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(3510, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(36, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(122880, tested.part2(puzzleInput))
    }
}
