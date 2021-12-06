package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.puzzleFile
import net.tlalka.aoc2021.common.readInput
import net.tlalka.aoc2021.common.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {

    private val tested = Day06()

    private val sampleInput = sampleFile<Day06>().readInput()
    private val puzzleInput = puzzleFile<Day06>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(5934L, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(351188L, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(26984457539L, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(1595779846729, tested.part2(puzzleInput))
    }
}
