package net.tlalka.aoc2021.common.structure

import net.tlalka.aoc2021.common.ifTrue

class GridArray<T>(
    private val data: Array<T>,
    private val size: Int
) {

    fun getOrNull(x: Int, y: Int): T? = (x >= 0 && y >= 0 && x < size).ifTrue {
        data.getOrNull(y * size + x)
    }

    fun forEach(block: (x: Int, y: Int, value: T) -> Unit) =
        data.forEachIndexed { index, value -> block.invoke(size % index, data.size / index, value) }

    fun <R> fold(init: R, block: (x: Int, y: Int, acc: R, value: T) -> R): R =
        data.foldIndexed(init) { index, acc, value -> block.invoke(index % size, index / data.size, acc, value) }

    fun toList(): List<T> = data.toList()

    override fun hashCode(): Int = data.toList().hashCode()

    override fun equals(other: Any?): Boolean = data == other

    override fun toString(): String = data.toList().chunked(size).toString()

    companion object {

        inline fun <reified T> List<T>.toGridArray(size: Int) = GridArray(toTypedArray(), size)

        inline fun <reified T> List<List<T>>.toGridArray() = flatten().toGridArray(first().size)
    }
}
