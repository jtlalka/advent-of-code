package net.tlalka.aoc2021.common.structure

import net.tlalka.aoc2021.common.ifTrue

class GridArray<T>(
    private val data: Array<T>,
    private val size: Int
) {

    fun getGridArea(x: Int, y: Int, radius: Int = 1): List<T> =
        (-radius..radius).flatMap { dY ->
            (-radius..radius).mapNotNull { dX ->
                getOrNull(x + dX, y + dY)
            }
        }

    private fun getOrNull(x: Int, y: Int) = (x >= 0 && y >= 0 && x < size).ifTrue {
        data.getOrNull(y * size + x)
    }

    fun toList(): List<T> = data.toList()

    override fun hashCode(): Int = data.toList().hashCode()

    override fun equals(other: Any?): Boolean = data == other

    override fun toString(): String = data.toList().chunked(size).toString()

    companion object {

        inline fun <reified T> List<T>.toGridArray(size: Int) = GridArray(toTypedArray(), size)

        inline fun <reified T> List<List<T>>.toGridArray() = flatten().toGridArray(first().size)
    }
}
