package net.tlalka.puzzles.common.extension

import net.tlalka.puzzles.common.utils.Files
import net.tlalka.puzzles.common.utils.Files.loadFile
import java.io.File

inline fun <reified T : Any> puzzleFile(): File = loadFile(T::class, postfix = "-puzzle.txt")
inline fun <reified T : Any> sampleFile(): File = loadFile(T::class, postfix = "-sample.txt")

fun File.readInput(): List<String> = Files.readFileInput(this)
