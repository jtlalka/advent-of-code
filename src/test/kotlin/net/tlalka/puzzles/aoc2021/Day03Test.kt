package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.MethodOrderer.MethodName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertEquals

@TestMethodOrder(MethodName::class)
class Day03Test {

    private val tested = Day03()

    private val sampleInput = sampleFile<Day03>().readInput()
    private val puzzleInput = puzzleFile<Day03>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(198, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(4118544, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(230, tested.part2(sampleInput))
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(3832770, tested.part2(puzzleInput))
    }
}
