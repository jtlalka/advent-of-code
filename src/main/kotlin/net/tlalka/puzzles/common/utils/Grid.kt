package net.tlalka.puzzles.common.utils

import net.tlalka.puzzles.common.extension.ifTrue
import net.tlalka.puzzles.common.extension.toInt

data class Grid<T>(
    private val data: MutableList<T>,
    val dimension: Dimension
) {

    private inline val Int.x: Int get() = this % dimension.x
    private inline val Int.y: Int get() = this / dimension.y

    fun getOrNull(x: Int, y: Int): T? =
        (x in 0..<dimension.x && y in 0..dimension.y).ifTrue {
            data.getOrNull(index = y * dimension.x + x)
        }

    fun setValue(x: Int, y: Int, element: T) {
        val index = y * dimension.x + x
        if (index in 0..<data.lastIndex) {
            data.set(index = index, element = element)
        }
    }

    fun getGrid(x: Int, y: Int, dimension: Dimension): Grid<T> {
        val isValidX: Int.() -> Boolean = { this in x..<x + dimension.x }
        val isValidY: Int.() -> Boolean = { this in y..<y + dimension.y }
        val newData = data.filterIndexed { index, _ -> index.x.isValidX() && index.y.isValidY() }

        return Grid(data = newData.toMutableList(), dimension = dimension)
    }

    fun <R> map(block: (x: Int, y: Int, value: T) -> R): Grid<R> = Grid(
        data = data.mapIndexed { index, value -> block(index.x, index.y, value) }.toMutableList(),
        dimension = dimension
    )

    fun forEach(block: (x: Int, y: Int, value: T) -> Unit) =
        data.forEachIndexed { index, value ->
            block(index.x, index.y, value)
        }

    fun sumOf(block: (x: Int, y: Int, value: T) -> Int): Int =
        data.foldIndexed(initial = 0) { index, acc, value ->
            acc + block(index.x, index.y, value)
        }

    fun count(block: (x: Int, y: Int, value: T) -> Boolean): Int =
        data.foldIndexed(initial = 0) { index, acc, value ->
            acc + block(index.x, index.y, value).toInt()
        }

    fun indexOf(element: T): Point = data.indexOf(element)
        .let { index -> Point(index.x, index.y) }

    fun toList(): List<List<T>> = data.chunked(dimension.x)

    override fun toString(): String = data.chunked(dimension.x)
        .joinToString(separator = "\n") { it.joinToString(separator = " ") }

    data class Dimension(val x: Int, val y: Int) {
        constructor(size: Int) : this(size, size)
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(point: Point) = Point(x = x + point.x, y = y + point.y)
        operator fun minus(point: Point) = Point(x = x - point.x, y = y - point.y)
    }

    companion object {

        inline fun <reified T> List<List<T>>.toGrid() = flatten().toGrid(maxOf { it.size })

        inline fun <reified T> List<T>.toGrid(line: Int) = Grid(
            data = toMutableList(),
            dimension = Dimension(x = line, y = size / line)
        )
    }
}