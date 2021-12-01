import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines of string from the given input txt file.
 */
fun readInput(name: String): List<String> = File("src", "$name.txt")
    .readLines()
    .filter { it.isNotBlank() }

/**
 * Reads lines of integers from the given input txt file.
 */
fun readIntInput(name: String): List<Int> = readInput(name)
    .map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
