package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {

    private val tested = Day17()

    private val sampleInput = sampleFile<Day17>().readInput()
    private val puzzleInput = puzzleFile<Day17>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(45, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(11175, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(112, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(3540, tested.part2(puzzleInput))
    }
}
