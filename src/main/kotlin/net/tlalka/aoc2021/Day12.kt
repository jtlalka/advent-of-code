package net.tlalka.aoc2021

import net.tlalka.aoc2021.common.toPair


class Day12 {

    fun part1(input: List<String>): Int {
        val graph = input.parsInput()
        val target = mutableSetOf<List<String>>()

        graph.findPaths(listOf(START_VERTEX), target) { cave, path ->
            cave[0].isUpperCase() || cave !in path
        }
        return target.count()
    }

    fun part2(input: List<String>): Int {
        val graph = input.parsInput()
        val target = mutableSetOf<List<String>>()
        val smallCaves = graph.keys.filter { it[0].isLowerCase() && it !in setOf(START_VERTEX, END_VERTEX) }

        smallCaves.forEach { smallCave ->
            graph.findPaths(listOf(START_VERTEX), target) { cave, path ->
                cave != START_VERTEX && (cave[0].isUpperCase() || cave !in (path - smallCave))
            }
        }
        return target.count()
    }

    private fun Map<String, HashSet<String>>.findPaths(
        path: List<String>,
        target: MutableSet<List<String>>,
        filter: (cave: String, path: List<String>) -> Boolean
    ) {
        val vertex = path.last()
        if (vertex == END_VERTEX) {
            target += path
            return
        }
        getValue(vertex)
            .filter { filter.invoke(it, path) }
            .forEach { findPaths(path + it, target, filter) }
    }

    private fun List<String>.parsInput(): Map<String, HashSet<String>> {
        val pairs = map { it.split("-").toPair() }

        return buildMap {
            pairs.forEach { (from, to) ->
                getOrPut(from) { HashSet() }.add(to)
                getOrPut(to) { HashSet() }.add(from)
            }
        }
    }

    companion object {
        private const val START_VERTEX = "start"
        private const val END_VERTEX = "end"
    }
}
