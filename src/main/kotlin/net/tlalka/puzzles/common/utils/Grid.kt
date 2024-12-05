package net.tlalka.puzzles.common.utils

import net.tlalka.puzzles.common.extension.ifTrue
import net.tlalka.puzzles.common.extension.toInt

data class Grid<T>(
    private val data: List<T>,
    private val size: Pair<Int, Int>
) {

    private inline val Pair<*, *>.x: Int get() = size.first
    private inline val Pair<*, *>.y: Int get() = size.second

    private inline val Int.x: Int get() = this % size.first
    private inline val Int.y: Int get() = this / size.second

    fun getOrNull(x: Int, y: Int): T? =
        (x in 0..<size.x && y in 0..<size.y).ifTrue {
            data.getOrNull(index = y * size.x + x)
        }

    fun getGrid(x: Int, y: Int, sizeX: Int, sizeY: Int): Grid<T> {
        val isValidX: Int.() -> Boolean = { this in x..<x + sizeX }
        val isValidY: Int.() -> Boolean = { this in y..<y + sizeY }

        return Grid(
            data = data.filterIndexed { index, _ -> index.x.isValidX() && index.y.isValidY() },
            size = sizeX to sizeY
        )
    }

    fun <R> map(block: (x: Int, y: Int, value: T) -> R): Grid<R> = Grid(
        data = data.mapIndexed { index, value -> block(index.x, index.y, value) },
        size = size
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

    override fun toString(): String = data.chunked(size.x).toString()

    companion object {

        inline fun <reified T> List<List<T>>.toGrid() = flatten().toGrid(first().size)

        inline fun <reified T> List<T>.toGrid(line: Int) = Grid(data = this, size = line to size / line)
    }
}