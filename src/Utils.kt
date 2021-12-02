import java.io.File

fun readStringInput(name: String): List<String> = readRawInput(name)

fun readIntInput(name: String): List<Int> = readRawInput(name)
    .map(String::toInt)

fun readComplexInput(name: String, delimiter: String = " "): List<List<String>> = readRawInput(name)
    .map { it.split(delimiter) }

private fun readRawInput(name: String): List<String> = File("src", "$name.txt")
    .readLines()
    .filter(String::isNotBlank)
