package net.tlalka.puzzles.aoc2021

import net.tlalka.puzzles.common.extension.puzzleFile
import net.tlalka.puzzles.common.extension.readInput
import net.tlalka.puzzles.common.extension.sampleFile
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {

    private val tested = Day13()

    private val sampleInput = sampleFile<Day13>().readInput()
    private val puzzleInput = puzzleFile<Day13>().readInput()

    @Test
    fun `verify part 1 with sample input`() {
        assertEquals(17, tested.part1(sampleInput))
    }

    @Test
    fun `verify part 1 with puzzle input`() {
        assertEquals(671, tested.part1(puzzleInput))
    }

    @Test
    fun `verify part 2 with sample input`() {
        assertEquals(
            expected = """
                            #####
                            #   #
                            #   #
                            #   #
                            #####
                            
                       """.trimIndent(),
            actual = tested.part2(sampleInput)
        )
    }

    @Test
    fun `verify part 2 with puzzle input`() {
        assertEquals(
            expected = """
                            ###   ##  ###  #  #  ##  ###  #  # #   
                            #  # #  # #  # #  # #  # #  # # #  #   
                            #  # #    #  # #### #  # #  # ##   #   
                            ###  #    ###  #  # #### ###  # #  #   
                            #    #  # #    #  # #  # # #  # #  #   
                            #     ##  #    #  # #  # #  # #  # ####
                            
                       """.trimIndent(),
            actual = tested.part2(puzzleInput)
        )
    }
}
