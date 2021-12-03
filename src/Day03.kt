/** Day 3 */
fun main() {

    fun part1(input: List<String>): Int {
        val inputRange = input.first().indices
        val inputSize = input.size
        var gammaRate = 0
        var epsilonRate = 0

        inputRange
            .map { index -> input.count { it[index] == '1' } }
            .map { it + it > inputSize }
            .reversed()
            .forEachIndexed { index, n ->
                gammaRate = gammaRate or (n.toInt() shl index)
                epsilonRate = epsilonRate or ((!n).toInt() shl index)
            }

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val inputRange = input.first().indices
        var oxygen = input.toList()
        var carbon = input.toList()

        inputRange.forEach { index ->
            if (oxygen.size > 1) {
                oxygen.partition { it[index] == '1' }.run {
                    oxygen = if (first.size >= second.size) first else second
                }
            }
        }

        inputRange.forEach { index ->
            if (carbon.size > 1) {
                carbon.partition { it[index] == '0' }.run {
                    carbon = if (first.size <= second.size) first else second
                }
            }
        }
        return oxygen.first().toInt(2) * carbon.first().toInt(2)
    }

    listOf("Day03-test", "Day03-data")
        .map(::readStringInput)
        .onEach {
            println(part1(it)) // 22 * 9 = 198
            println(part2(it)) // 23 * 10 = 230
        }
}

private fun Boolean.toInt() = if (this) 1 else 0
