package net.tlalka.puzzles.common.utils

import net.tlalka.puzzles.common.extension.ifTrue
import net.tlalka.puzzles.common.extension.toInt

data class Grid<T>(
    private val data: List<T>,
    private val sizeX: Int,
    private val sizeY: Int
) {

    fun getOrNull(x: Int, y: Int): T? =
        (x in 0..<sizeX && y in 0..<sizeY).ifTrue {
            data.getOrNull(index = y * sizeX + x)
        }

    fun getGrid(x: Int, y: Int, sizeX: Int, sizeY: Int): Grid<T> {
        val isValidX: Int.() -> Boolean = { x() in x..<x + sizeX }
        val isValidY: Int.() -> Boolean = { y() in y..<y + sizeY }

        return Grid(
            data = data.filterIndexed { index, _ -> index.isValidX() && index.isValidY() },
            sizeX = sizeX,
            sizeY = sizeY
        )
    }

    fun <R> map(block: (x: Int, y: Int, value: T) -> R): Grid<R> = Grid(
        data = data.mapIndexed { index, value -> block(index.x(), index.y(), value) },
        sizeX = sizeX,
        sizeY = sizeY
    )

    fun forEach(block: (x: Int, y: Int, value: T) -> Unit) =
        data.forEachIndexed { index, value ->
            block(index.x(), index.y(), value)
        }

    fun sumOf(block: (x: Int, y: Int, value: T) -> Int): Int =
        data.foldIndexed(initial = 0) { index, acc, value ->
            acc + block(index.x(), index.y(), value)
        }

    fun count(block: (x: Int, y: Int, value: T) -> Boolean): Int =
        data.foldIndexed(initial = 0) { index, acc, value ->
            acc + block(index.x(), index.y(), value).toInt()
        }

    private fun Int.x(): Int = this % sizeX
    private fun Int.y(): Int = this / sizeY

    override fun toString(): String = data.chunked(sizeX).toString()

    companion object {

        inline fun <reified T> List<List<T>>.toGrid() = flatten().toGrid(first().size)

        inline fun <reified T> List<T>.toGrid(line: Int) = Grid(data = this, sizeX = line, sizeY = size / line)
    }
}