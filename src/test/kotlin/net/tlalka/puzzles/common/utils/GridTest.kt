package net.tlalka.puzzles.common.utils

import net.tlalka.puzzles.common.utils.Grid.Companion.toGrid
import net.tlalka.puzzles.common.utils.Grid.Dimension
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GridTest {

    private val tested = listOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
        listOf(0)
    ).toGrid()

    @Test
    fun `returns expected value when getOrNull function is triggered`() {
        val result = (0..5).flatMap { y ->
            (0..5).mapNotNull { x ->
                tested.getOrNull(x, y)
            }
        }

        assertEquals(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0), result)
    }

    @Test
    fun `returns reduced grid when new grid size is bigger then original grid`() {
        val result = tested.getGrid(x = 0, y = 0, Dimension(100))

        assertEquals("[[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]]", result.toList().toString())
    }

    @Test
    fun `returns reduced grid when new grid size is smaller then original grid`() {
        val result = tested.getGrid(x = 0, y = 1, Dimension(2, 3))

        assertEquals("[[4, 5], [7, 8], [0]]", result.toList().toString())
    }

    @Test
    fun `returns reduced grid when new grid size is contains single element`() {
        val result = tested.getGrid(x = 0, y = 3, Dimension(1, 1))

        assertEquals("[[0]]", result.toList().toString())
    }

    @Test
    fun `returns reduced grid when new grid start position is out of original grid`() {
        val result = tested.getGrid(x = 100, y = 100, Dimension(2))

        assertEquals("[]", result.toList().toString())
    }

    @Test
    fun `returns mapped grid when map function is triggered`() {
        val result = tested.map { x, y, value -> value.takeIf { x < 2 } }

        assertEquals("[[1, 2, null], [4, 5, null], [7, 8, null], [0]]", result.toList().toString())
    }

    @Test
    fun `returns expected values and indexes for map function`() {
        var xIndex = ""
        var yIndex = ""
        var values = ""

        tested.map { x, y, value ->
            xIndex += x
            yIndex += y
            values += value
        }

        assertEquals(
            expected = listOf("0120120120", "0001112223", "1234567890"),
            actual = listOf(xIndex, yIndex, values)
        )
    }

    @Test
    fun `returns expected values and indexes for forEach function`() {
        var xIndex = ""
        var yIndex = ""
        var values = ""

        tested.forEach { x, y, value ->
            xIndex += x
            yIndex += y
            values += value
        }

        assertEquals(
            expected = listOf("0120120120", "0001112223", "1234567890"),
            actual = listOf(xIndex, yIndex, values)
        )
    }

    @Test
    fun `returns sum result when sumOf function is triggered`() {
        val result = tested.sumOf { _, _, value -> value }

        assertEquals(result, 45)
    }

    @Test
    fun `returns expected values and indexes for sumOf function`() {
        var xIndex = ""
        var yIndex = ""
        var values = ""

        tested.sumOf { x, y, value ->
            value.also {
                xIndex += x
                yIndex += y
                values += value
            }
        }

        assertEquals(
            expected = listOf("0120120120", "0001112223", "1234567890"),
            actual = listOf(xIndex, yIndex, values)
        )
    }

    @Test
    fun `returns count result when count function is triggered`() {
        val result = tested.count { _, _, value -> value % 2 == 0 }

        assertEquals(result, 5)
    }

    @Test
    fun `returns expected values and indexes for count function`() {
        var xIndex = ""
        var yIndex = ""
        var values = ""

        tested.count { x, y, value ->
            true.also {
                xIndex += x
                yIndex += y
                values += value
            }
        }

        assertEquals(
            expected = listOf("0120120120", "0001112223", "1234567890"),
            actual = listOf(xIndex, yIndex, values)
        )
    }

    @Test
    fun `returns chunked list string when toString function is triggered`() {
        val result = tested.toString()

        assertEquals(
            expected = """
                1 2 3
                4 5 6
                7 8 9
                0
            """.trimIndent(),
            actual = result
        )
    }

    @Test
    fun `compare different representation of the same grid`() {
        val result = "1234567890".map { "$it".toInt() }.toGrid(line = 3)

        assertEquals(result, tested)
    }
}