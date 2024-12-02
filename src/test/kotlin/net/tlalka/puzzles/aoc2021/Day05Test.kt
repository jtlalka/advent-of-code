package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.MethodOrderer.MethodName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertEquals

@TestMethodOrder(MethodName::class)
class Day05Test {

    private val tested = Day05()

    private val sampleInput = sampleFile<Day05>().readInput()
    private val puzzleInput = puzzleFile<Day05>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(5, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(6283, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(12, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(18864, tested.part2(puzzleInput))
    }
}
