fun main() {

    fun part1(game: BingoGame): Int {
        val boards = game.boards
            .map { Triple(it.toTypedArray<Int?>(), IntArray(5), IntArray(5)) }
            .toList()

        game.numbers.forEach { number ->
            boards.forEach { board ->
                val (data, rowSum, colSum) = board
                val index = data.indexOf(number)

                if (index != -1) {
                    data[index] = null
                    rowSum[index / 5] += 1
                    colSum[index % 5] += 1

                    if (rowSum.contains(5) || colSum.contains(5)) {
                        return data.filterNotNull().sum() * number
                    }
                }
            }
        }
        return 0
    }

    fun part2(game: BingoGame): Int {
        var boards = game.boards
            .map { Triple(it.toTypedArray<Int?>(), IntArray(5), IntArray(5)) }
            .toList()

        game.numbers.forEach { number ->
            boards.forEach { board ->
                val (data, rowSum, colSum) = board
                val index = data.indexOf(number)

                if (index != -1) {
                    data[index] = null
                    rowSum[index / 5] += 1
                    colSum[index % 5] += 1

                    if (rowSum.contains(5) || colSum.contains(5)) {
                        if (boards.size == 1) {
                            return data.filterNotNull().sum() * number
                        } else {
                            boards = boards - board
                        }
                    }
                }
            }
        }
        return 0
    }

    fun transform(input: List<String>): BingoGame = BingoGame(
        numbers = input
            .first()
            .split(",")
            .map(String::toInt),
        boards = input
            .drop(1)
            .asSequence()
            .map { it.split("\\s+".toRegex()) }
            .flatten()
            .map(String::toInt)
            .chunked(25)
    )

    listOf("Day04-test", "Day04-data")
        .map(::readStringInput)
        .map(::transform)
        .onEach {
            println(">> P1: " + part1(it)) // 188 * 24 = 4512
            println(">> P2: " + part2(it)) // 148 * 13 = 1924
        }
}

private data class BingoGame(
    val numbers: List<Int>,
    val boards: Sequence<List<Int>>
)
