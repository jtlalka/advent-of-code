package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    private val tested = Day10()

    private val sampleInput = sampleFile<Day10>().readInput()
    private val puzzleInput = puzzleFile<Day10>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(26397L, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(318099L, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(288957L, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(2389738699L, tested.part2(puzzleInput))
    }
}
