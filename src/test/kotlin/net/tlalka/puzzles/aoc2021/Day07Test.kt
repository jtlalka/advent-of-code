package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {

    private val tested = Day07()

    private val sampleInput = sampleFile<Day07>().readInput()
    private val puzzleInput = puzzleFile<Day07>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(37, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(344605, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(168, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(93699985, tested.part2(puzzleInput))
    }
}
