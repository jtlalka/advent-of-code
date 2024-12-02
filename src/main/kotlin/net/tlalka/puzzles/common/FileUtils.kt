package net.tlalka.puzzles.common

import java.io.File
import kotlin.reflect.KClass

object FileUtils {

    fun loadFile(
        clazz: KClass<*>,
        postfix: String
    ): File = loadFile(
        buildString {
            append(
                clazz.qualifiedName
                    .orEmpty()
                    .substringBeforeLast('.')
                    .substringAfterLast('.')
            )
            append(File.separator)
            append(clazz.simpleName)
            append(postfix)
        }
    )

    fun loadFile(filePath: String): File = this::class.java
        .classLoader
        .getResource(filePath)
        ?.toURI()
        ?.let { File(it) }
        ?: throw IllegalArgumentException("File not found: $filePath")

    fun readFileInput(file: File): List<String> = file
        .readLines()
        .map(String::trim)
        .filter(String::isNotBlank)
}
