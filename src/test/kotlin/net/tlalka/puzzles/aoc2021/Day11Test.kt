package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {

    private val tested = Day11()

    private val sampleInput = sampleFile<Day11>().readInput()
    private val puzzleInput = puzzleFile<Day11>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(1656, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(1773, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(195, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(494, tested.part2(puzzleInput))
    }
}
