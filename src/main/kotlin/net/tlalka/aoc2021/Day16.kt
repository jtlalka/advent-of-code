package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.toLong

class Day16 {

    fun part1(input: List<String>): Int = input.toBinaryChars().run {
        Packet.build(iterator()).allVersions().sum()
    }

    fun part2(input: List<String>): Long = input.toBinaryChars().run {
        Packet.build(iterator()).calculate()
    }

    private fun List<String>.toBinaryChars(): List<Char> = this
        .single()
        .map { it.digitToInt(16).toString(2) }
        .flatMap { it.padStart(4, '0').toList() }

    private sealed class Packet(val version: Int) {

        abstract fun allVersions(): List<Int>
        abstract fun calculate(): Long

        class Literal(version: Int, val value: Long) : Packet(version) {

            override fun allVersions(): List<Int> = listOf(version)

            override fun calculate(): Long = value
        }

        class Operation(version: Int, val type: Int, val packets: List<Packet>) : Packet(version) {

            override fun allVersions(): List<Int> = listOf(version) + packets.flatMap { it.allVersions() }

            override fun calculate(): Long = when (type) {
                0 -> packets.sumOf { it.calculate() }
                1 -> packets.fold(1L) { acc, next -> acc * next.calculate() }
                2 -> packets.minOf { it.calculate() }
                3 -> packets.maxOf { it.calculate() }
                5 -> (packets.first().calculate() > packets.last().calculate()).toLong()
                6 -> (packets.first().calculate() < packets.last().calculate()).toLong()
                7 -> (packets.first().calculate() == packets.last().calculate()).toLong()
                else -> error("BUM >> $type")
            }
        }

        companion object {

            private fun Iterator<Char>.nextAsInt(size: Int): Int = next(size).toInt()
            private fun Iterator<Char>.next(size: Int): List<Char> = (0 until size).map { next() }

            private fun List<Char>.toInt() = joinToString("").toInt(2)
            private fun List<Char>.toLong() = joinToString("").toLong(2)

            fun build(iterator: Iterator<Char>): Packet {
                val version = iterator.nextAsInt(3)
                val type = iterator.nextAsInt(3)

                return when (type == 4) {
                    true -> buildLiteral(iterator, version)
                    false -> buildOperation(iterator, version, type)
                }
            }

            private fun buildLiteral(iterator: Iterator<Char>, version: Int): Literal = Literal(
                version = version,
                value = buildList {
                    while (iterator.nextAsInt(1) == 1) addAll(iterator.next(4))
                    addAll(iterator.next(4))
                }.toLong()
            )

            private fun buildOperation(iterator: Iterator<Char>, version: Int, type: Int): Operation {
                return when (iterator.nextAsInt(1)) {
                    0 -> {
                        val subPacketLength = iterator.nextAsInt(15)
                        val subPacketIterator = iterator.next(subPacketLength).iterator()

                        Operation(version, type, buildList {
                            while (subPacketIterator.hasNext()) {
                                add(build(subPacketIterator))
                            }
                        })
                    }
                    1 -> {
                        val numberOfPackets = iterator.nextAsInt(11)
                        val subPackets = (1..numberOfPackets).map { build(iterator) }
                        Operation(version, type, subPackets)
                    }
                    else -> error("BUM!!")
                }
            }
        }
    }
}
