package net.tlalka.aoc2021.common

fun <T> List<T>.toPair(): Pair<T, T> = Pair(first(), last())
