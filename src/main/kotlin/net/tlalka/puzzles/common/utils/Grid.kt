package net.tlalka.puzzles.common.utils

import net.tlalka.puzzles.common.extension.ifTrue
import net.tlalka.puzzles.common.extension.toInt

data class Grid<T>(
    private val data: List<T>,
    private val size: Size
) {

    private inline val Int.point: Point get() = Point(x = this % size.x, y = this / size.x)
    private inline val Point.index: Int get() = y * size.x + x

    constructor(data: List<List<T>>) : this(
        data = data.flatten(),
        size = Size(x = data.maxOf { it.size }, y = data.size)
    )

    fun getOrNull(point: Point): T? =
        size.contains(point).ifTrue {
            data.getOrNull(index = point.index)
        }

    fun setValue(point: Point, element: T) {
        if (point.index <= data.lastIndex) {
            (data as MutableList<T>).set(index = point.index, element = element)
        }
    }

    fun getGrid(point: Point, size: Size) = Grid(
        data = data.filterIndexed { index, _ -> size.contains(index.point - point) },
        size = size
    )

    fun <R> map(block: (point: Point, value: T) -> R): Grid<R> = Grid(
        data = data.mapIndexed { index, value -> block(index.point, value) },
        size = size
    )

    fun forEach(block: (point: Point, value: T) -> Unit) =
        data.forEachIndexed { index, value ->
            block(index.point, value)
        }

    fun sumOf(block: (point: Point, value: T) -> Int): Int =
        data.foldIndexed(initial = 0) { index, acc, value ->
            acc + block(index.point, value)
        }

    fun count(block: (point: Point, value: T) -> Boolean): Int =
        data.foldIndexed(initial = 0) { index, acc, value ->
            acc + block(index.point, value).toInt()
        }

    fun indexOf(element: T): Point = data.indexOf(element).point

    fun lastIndex(): Point = data.lastIndex.point

    fun size(): Size = size.copy()

    fun toList(): List<List<T>> = data.chunked(size.x)

    fun copy(): Grid<T> = Grid(data = data.toList(), size = size)

    override fun toString(): String = data.chunked(size.x)
        .joinToString(separator = "\n") { it.joinToString(separator = " ") }

    data class Size(val x: Int, val y: Int) {
        fun contains(point: Point): Boolean = point.x in 0..<x && point.y in 0..<y
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(point: Point) = Point(x = x + point.x, y = y + point.y)
        operator fun minus(point: Point) = Point(x = x - point.x, y = y - point.y)
    }

    companion object Builder {
        inline fun <reified T> List<T>.toGrid(xSize: Int) = Grid(data = chunked(xSize))
        inline fun <reified T> List<List<T>>.toGrid() = Grid(data = this)
    }
}