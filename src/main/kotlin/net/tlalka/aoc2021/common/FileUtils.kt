package net.tlalka.aoc2021.common

import java.io.File

object FileUtils {

    fun loadFile(fileName: String): File = this::class
        .java
        .classLoader
        .getResource(fileName)
        .run { File(requireNotNull(this?.toURI())) }

    fun readFileInput(file: File): List<String> = file
        .readLines()
        .map(String::trim)
        .filter(String::isNotBlank)
}
