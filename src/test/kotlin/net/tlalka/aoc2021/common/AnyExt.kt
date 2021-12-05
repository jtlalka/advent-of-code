package net.tlalka.aoc2021.common

import java.io.File

inline fun <reified T : Any> puzzleFile(): File = FileUtils.loadFile(T::class.simpleName + ".txt")
inline fun <reified T : Any> sampleFile(): File = FileUtils.loadFile(T::class.simpleName + "-sample.txt")
