package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.puzzleFile
import net.tlalka.aoc2021.common.readInput
import net.tlalka.aoc2021.common.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {

    private val tested = Day09()

    private val sampleInput = sampleFile<Day09>().readInput()
    private val puzzleInput = puzzleFile<Day09>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(15, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(458, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(1134, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(1391940, tested.part2(puzzleInput))
    }
}
