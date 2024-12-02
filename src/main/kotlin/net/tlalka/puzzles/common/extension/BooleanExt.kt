package net.tlalka.puzzles.common.extension

fun Boolean.toInt() = if (this) 1 else 0
fun Boolean.toLong() = if (this) 1L else 0L

fun <R> Boolean.ifTrue(block: () -> R): R? = if (this) block.invoke() else null
fun <R> Boolean.ifFalse(block: () -> R): R? = if (this) null else block.invoke()
