package net.tlalka.puzzles.common.utils

import net.tlalka.puzzles.common.extension.orZero
import net.tlalka.puzzles.common.extension.toInt
import net.tlalka.puzzles.common.utils.Grid.Builder.toGrid
import net.tlalka.puzzles.common.utils.Grid.Point
import net.tlalka.puzzles.common.utils.Grid.Size
import org.junit.jupiter.api.MethodOrderer.MethodName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNotSame
import kotlin.test.assertTrue

class GridTest {

    private val tested = Grid(data = LIST_OF_LIST)

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridAccessTest {

        @Test
        fun `returns expected value when getOrNull function is triggered for every pointer`() {
            val result = (0..3).map { y ->
                (0..3).map { x ->
                    tested.getOrNull(Point(x, y))
                }
            }

            assertEquals(
                expected = listOf(
                    listOf(1, 2, 3, null),
                    listOf(4, 5, 6, null),
                    listOf(7, 8, 9, null),
                    listOf(0, null, null, null)
                ),
                actual = result
            )
        }

        @Test
        fun `returns expected value when getOrNull function is triggered with last pointer`() {
            val lastIndex = Point(x = 0, y = 3)

            val result = tested.getOrNull(lastIndex)

            assertEquals(expected = 0, actual = result)
        }

        @Test
        fun `returns null when getOrNull function is triggered with invalid X pointer`() {
            val invalid = Point(x = -1, y = 0)

            val result = tested.getOrNull(invalid)

            assertEquals(expected = null, actual = result)
        }

        @Test
        fun `returns null when getOrNull function is triggered with invalid Y pointer`() {
            val invalid = Point(x = 0, y = -1)

            val result = tested.getOrNull(invalid)

            assertEquals(expected = null, actual = result)
        }

        @Test
        fun `returns updated value when setValue is triggered with correct pointer`() {
            val pointer = Point(x = 0, y = 3)

            tested.setValue(pointer, 12)

            assertEquals(expected = 12, actual = tested.getOrNull(pointer))
        }

        @Test
        fun `returns null value when setValue is triggered with invalid X pointer`() {
            val pointer = Point(x = 100, y = 0)

            tested.setValue(pointer, 12)

            assertEquals(expected = null, actual = tested.getOrNull(pointer))
        }

        @Test
        fun `returns null value when setValue is triggered with invalid Y pointer`() {
            val pointer = Point(x = 0, y = 100)

            tested.setValue(pointer, 12)

            assertEquals(expected = null, actual = tested.getOrNull(pointer))
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridReduceTest {

        @Test
        fun `returns original grid when get grid is triggered with start pointer and same size`() {
            val result = tested.getGrid(Point(x = 0, y = 0), tested.size())

            assertEquals(expected = tested, actual = result)
        }

        @Test
        fun `returns reduced grid when get grid is triggered with smaller size`() {
            val result = tested.getGrid(Point(x = 0, y = 0), Size(x = 2, y = 2))

            assertEquals(
                expected = listOf(listOf(1, 2), listOf(4, 5)),
                actual = result.toList()
            )
        }

        @Test
        fun `returns reduced grid when get grid is triggered with single column size`() {
            val result = tested.getGrid(Point(x = 0, y = 0), Size(x = 1, y = 4))

            assertEquals(
                expected = listOf(listOf(1), listOf(4), listOf(7), listOf(0)),
                actual = result.toList()
            )
        }

        @Test
        fun `returns reduced grid when get grid is triggered with single row size`() {
            val result = tested.getGrid(Point(x = 0, y = 0), Size(x = 4, y = 1))

            assertEquals(
                expected = listOf(listOf(1, 2, 3)),
                actual = result.toList()
            )
        }

        @Test
        fun `returns reduced grid when get grid is triggered with pointer and single column size`() {
            val result = tested.getGrid(Point(x = 1, y = 0), Size(x = 1, y = 4))

            assertEquals(
                expected = listOf(listOf(2), listOf(5), listOf(8)),
                actual = result.toList()
            )
        }

        @Test
        fun `returns reduced grid when get grid is triggered with pointer and single row size`() {
            val result = tested.getGrid(Point(x = 0, y = 2), Size(x = 4, y = 1))

            assertEquals(
                expected = listOf(listOf(7, 8, 9)),
                actual = result.toList()
            )
        }

        @Test
        fun `returns reduced grid when get grid is triggered with custom pointer and size`() {
            val result = tested.getGrid(Point(x = 0, y = 1), Size(x = 2, y = 3))

            assertEquals(
                expected = listOf(listOf(4, 5), listOf(7, 8), listOf(0)),
                actual = result.toList()
            )
        }

        @Test
        fun `returns reduced grid when get grid is triggered for single element`() {
            val result = tested.getGrid(Point(x = 0, y = 3), Size(x = 1, y = 1))

            assertEquals(listOf(listOf(0)), result.toList())
        }

        @Test
        fun `returns empty grid when get grid is triggered with pointer out of grid`() {
            val result = tested.getGrid(Point(x = 100, y = 100), Size(x = 2, y = 2))

            assertEquals(emptyList(), result.toList())
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridTransformTest {

        @Test
        fun `returns mapped grid when map function is triggered for count operation`() {
            val result = tested.map { (x, y), value -> value + x + y }

            assertEquals(
                expected = listOf(
                    listOf(1, 3, 5),
                    listOf(5, 7, 9),
                    listOf(9, 11, 13),
                    listOf(3)
                ),
                actual = result.toList()
            )
        }

        @Test
        fun `returns mapped grid when map function is triggered for mapping operation`() {
            val result = tested.map { point, _ -> point }

            assertEquals(
                expected = listOf(
                    listOf(Point(x = 0, y = 0), Point(x = 1, y = 0), Point(x = 2, y = 0)),
                    listOf(Point(x = 0, y = 1), Point(x = 1, y = 1), Point(x = 2, y = 1)),
                    listOf(Point(x = 0, y = 2), Point(x = 1, y = 2), Point(x = 2, y = 2)),
                    listOf(Point(x = 0, y = 3))
                ),
                actual = result.toList()
            )
        }

        @Test
        fun `returns mapped grid when map function is triggered for reduce operation`() {
            val result = tested.map { (x, y), value -> value.takeIf { x + y < 3 }.orZero() }

            assertEquals(
                expected = listOf(
                    listOf(1, 2, 3),
                    listOf(4, 5, 0),
                    listOf(7, 0, 0),
                    listOf(0)
                ),
                actual = result.toList()
            )
        }

        @Test
        fun `returns expected pointers when map function is triggered`() {
            val result = buildList { tested.map { point, _ -> add(point) } }

            assertEquals(
                expected = listOf(
                    Point(x = 0, y = 0), Point(x = 1, y = 0), Point(x = 2, y = 0),
                    Point(x = 0, y = 1), Point(x = 1, y = 1), Point(x = 2, y = 1),
                    Point(x = 0, y = 2), Point(x = 1, y = 2), Point(x = 2, y = 2),
                    Point(x = 0, y = 3)
                ),
                actual = result
            )
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridIterationTest {

        @Test
        fun `returns expected values when forEach function is triggered`() {
            val result = buildList { tested.map { _, value -> add(value) } }

            assertEquals(
                expected = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0),
                actual = result.toList()
            )
        }

        @Test
        fun `returns expected pointers when forEach function is triggered`() {
            val result = buildList { tested.forEach { point, _ -> add(point) } }

            assertEquals(
                expected = listOf(
                    Point(x = 0, y = 0), Point(x = 1, y = 0), Point(x = 2, y = 0),
                    Point(x = 0, y = 1), Point(x = 1, y = 1), Point(x = 2, y = 1),
                    Point(x = 0, y = 2), Point(x = 1, y = 2), Point(x = 2, y = 2),
                    Point(x = 0, y = 3)
                ),
                actual = result
            )
        }

        @Test
        fun `returns sum of every elements when sumOf function is triggered`() {
            val result = tested.sumOf { _, value -> value }

            assertEquals(expected = result, actual = 45)
        }

        @Test
        fun `returns expected pointers when sumOf function is triggered`() {
            val result = buildList { tested.sumOf { point, _ -> add(point).toInt() } }

            assertEquals(
                expected = listOf(
                    Point(x = 0, y = 0), Point(x = 1, y = 0), Point(x = 2, y = 0),
                    Point(x = 0, y = 1), Point(x = 1, y = 1), Point(x = 2, y = 1),
                    Point(x = 0, y = 2), Point(x = 1, y = 2), Point(x = 2, y = 2),
                    Point(x = 0, y = 3)
                ),
                actual = result
            )
        }

        @Test
        fun `returns count of even values when count function is triggered`() {
            val result = tested.count { _, value -> value % 2 == 0 }

            assertEquals(expected = result, actual = 5)
        }

        @Test
        fun `returns expected pointers when count function is triggered`() {
            val result = buildList { tested.count { point, _ -> add(point) } }

            assertEquals(
                expected = listOf(
                    Point(x = 0, y = 0), Point(x = 1, y = 0), Point(x = 2, y = 0),
                    Point(x = 0, y = 1), Point(x = 1, y = 1), Point(x = 2, y = 1),
                    Point(x = 0, y = 2), Point(x = 1, y = 2), Point(x = 2, y = 2),
                    Point(x = 0, y = 3)
                ),
                actual = result
            )
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridIndexingTest {

        @Test
        fun `returns pointer when function is triggered for last element`() {
            val result = tested.indexOf(0)

            assertEquals(expected = Point(x = 0, y = 3), actual = result)
        }

        @Test
        fun `returns pointer when function is triggered for column grid`() {
            val given = listOf(1, 2, 3, 4, 5).toGrid(xSize = 1)

            val result = given.indexOf(4)

            assertEquals(expected = Point(x = 0, y = 3), actual = result)
        }

        @Test
        fun `returns pointer when function is triggered for row grid`() {
            val given = listOf(1, 2, 3, 4, 5).toGrid(xSize = 10)

            val result = given.indexOf(4)

            assertEquals(expected = Point(x = 3, y = 0), actual = result)
        }

        @Test
        fun `returns last pointer when function is triggered for last element`() {
            val result = tested.lastIndex()

            assertEquals(expected = Point(x = 0, y = 3), actual = result)
        }

        @Test
        fun `returns last pointer when function is triggered for column grid`() {
            val given = listOf(1, 2, 3, 4, 5).toGrid(xSize = 1)

            val result = given.lastIndex()

            assertEquals(expected = Point(x = 0, y = 4), actual = result)
        }

        @Test
        fun `returns last pointer when function is triggered for row grid`() {
            val given = listOf(1, 2, 3, 4, 5).toGrid(xSize = 10)

            val result = given.lastIndex()

            assertEquals(expected = Point(x = 4, y = 0), actual = result)
        }

        @Test
        fun `returns pointer when Grid values much grid size`() {
            val given = listOf(1, 2, 3, 4).toGrid(xSize = 2)

            val result = given.lastIndex()

            assertEquals(expected = Point(x = 1, y = 1), actual = result)
        }

        @Test
        fun `returns pointer when Grid values not much grid size`() {
            val given = listOf(1, 2, 3).toGrid(xSize = 2)

            val result = given.lastIndex()

            assertEquals(expected = Point(x = 0, y = 1), actual = result)
        }

        @Test
        fun `returns last index when Grid has single value`() {
            val given = listOf("1").toGrid(xSize = 2)

            val result = given.lastIndex()

            assertEquals(expected = Point(x = 0, y = 0), actual = result)
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridSizeTest {

        @Test
        fun `returns grid size when Grid values represent column`() {
            val given = listOf(1, 2, 3, 4, 5).toGrid(xSize = 1)

            val result = given.size()

            assertEquals(expected = Size(x = 1, y = 5), actual = result)
        }

        @Test
        fun `returns grid size when Grid values represent row`() {
            val given = listOf(1, 2, 3, 4, 5).toGrid(xSize = 10)

            val result = given.size()

            assertEquals(expected = Size(x = 5, y = 1), actual = result)
        }

        @Test
        fun `returns grid size when Grid values much grid size`() {
            val given = listOf(1, 2, 3, 4).toGrid(xSize = 2)

            val result = given.size()

            assertEquals(expected = Size(x = 2, y = 2), actual = result)
        }

        @Test
        fun `returns grid size when Grid values not much grid size`() {
            val given = listOf(1, 2, 3).toGrid(xSize = 2)

            val result = given.size()

            assertEquals(expected = Size(x = 2, y = 2), actual = result)
        }

        @Test
        fun `returns grid size when Grid has single value`() {
            val given = listOf("1").toGrid(xSize = 2)

            val result = given.size()

            assertEquals(expected = Size(x = 1, y = 1), actual = result)
        }

        @Test
        fun `compare returned grid size when input data much grid size`() {
            val input1 = listOf(1, 2, 3, 4).toGrid(xSize = 2)
            val input2 = listOf(listOf(1, 2), listOf(3, 4)).toGrid()

            val result1 = input1.size()
            val result2 = input2.size()

            println(result2)

            assertTrue("$result1 - $result2") { result1 == result2 }
        }

        @Test
        fun `compare returned grid size when input data not much grid size`() {
            val input1 = listOf(1, 2, 3).toGrid(xSize = 2)
            val input2 = listOf(listOf(1, 2), listOf(3)).toGrid()

            val result1 = input1.size()
            val result2 = input2.size()

            assertTrue("$result1 - $result2") { result1 == result2 }
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridKotlinFunctionTest {

        @Test
        fun `returns formated string when toString function is triggered`() {
            val result = tested.toString()

            assertEquals(
                expected = "1 2 3\n4 5 6\n7 8 9\n0",
                actual = result
            )
        }

        @Test
        fun `returns new Grid with when copy function is triggered with the same content`() {
            val result = tested.copy()

            assertEquals(expected = tested, actual = result)
        }

        @Test
        fun `returns new instantiation of Grid class when copy function is triggered`() {
            val result = tested.copy()

            assertNotSame(illegal = tested::class, actual = result::class)
        }

        @Test
        fun `updates only copied grid when copy function is triggered`() {
            val given = tested.copy()

            val result = given.apply { setValue(Point(0, 0), 12) }

            assertNotEquals(illegal = tested, actual = result)
        }
    }

    @Nested
    @TestMethodOrder(MethodName::class)
    inner class GridExtensionTest {

        @Test
        fun `returns same structure when Grid is created by flatten list extension function`() {
            val given = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)

            val result = given.toGrid(xSize = 3)

            assertEquals(expected = result, actual = tested)
        }

        @Test
        fun `returns same structure when Grid is created by list of list extension function`() {
            val given = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0).chunked(size = 3)

            val result = given.toGrid()

            assertEquals(expected = result, actual = tested)
        }

        @Test
        fun `compare results of extension function when input much grid size`() {
            val input1 = listOf(1, 2, 3, 4)
            val input2 = listOf(listOf(1, 2), listOf(3, 4))

            val result1 = input1.toGrid(xSize = 2)
            val result2 = input2.toGrid()

            println(result2)

            assertTrue("$result1\n$result2") { result1 == result2 }
        }

        @Test
        fun `compare results of extension function when input not much grid size`() {
            val input1 = listOf(1, 2, 3)
            val input2 = listOf(listOf(1, 2), listOf(3))

            val result1 = input1.toGrid(xSize = 2)
            val result2 = input2.toGrid()

            assertTrue("$result1\n$result2") { result1 == result2 }
        }

        @Test
        fun `compare results of extension function when input single input`() {
            val input1 = listOf("1")
            val input2 = listOf(listOf("1"))

            val result1 = input1.toGrid(xSize = 2)
            val result2 = input2.toGrid()

            assertTrue("$result1\n$result2") { result1 == result2 }
        }
    }

    companion object {
        private val LIST_OF_LIST = listOf(
            listOf(1, 2, 3),
            listOf(4, 5, 6),
            listOf(7, 8, 9),
            listOf(0)
        )
    }
}