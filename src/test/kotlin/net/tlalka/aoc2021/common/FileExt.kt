package net.tlalka.aoc2021.common

import java.io.File

fun File.readInput(): List<String> = FileUtils.readFileInput(this)
