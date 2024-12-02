package net.tlalka.puzzles.aoc2021

class Day04 {

    fun part1(input: List<String>): Int = with(input) {
        val numbers = gameNumbers()
        val boards = gameBoards()

        numbers.forEach { number ->
            boards.forEach { board ->
                if (board.containsNumber(number)) {
                    board.crossNumber(number)

                    if (board.isBingo()) {
                        return board.getUncrossed().sum() * number
                    }
                }
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int = with(input) {
        val numbers = gameNumbers()
        var boards = gameBoards()

        numbers.forEach { number ->
            boards.forEach { board ->
                if (board.containsNumber(number)) {
                    board.crossNumber(number)

                    if (board.isBingo()) {
                        if (boards.size == 1) {
                            return board.getUncrossed().sum() * number
                        } else {
                            boards = boards - board
                        }
                    }
                }
            }
        }
        return 0
    }

    private fun List<String>.gameNumbers() = this
        .first()
        .split(",")
        .map(String::toInt)

    private fun List<String>.gameBoards(): List<BingoBoard> = this
        .drop(1)
        .asSequence()
        .map { it.split("\\s+".toRegex()) }
        .flatten()
        .map(String::toInt)
        .chunked(25)
        .map { BingoBoard(it.toTypedArray()) }
        .toList()

    private class BingoBoard(
        private val data: Array<Int?>,
        private val rowSum: IntArray = IntArray(5),
        private val colSum: IntArray = IntArray(5)
    ) {

        fun crossNumber(number: Int) {
            if (data.contains(number)) {
                val index = data.indexOf(number)

                data[index] = null
                rowSum[index / 5] += 1
                colSum[index % 5] += 1
            }
        }

        fun containsNumber(number: Int): Boolean = data.contains(number)

        fun isBingo(): Boolean = rowSum.contains(5) || colSum.contains(5)

        fun getUncrossed(): List<Int> = data.filterNotNull()
    }
}
