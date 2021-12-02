/** Day 2 */
fun main() {

    fun part1(input: List<InputData>): Int {
        var horizontal = 0
        var depth = 0

        input.forEach {
            when (it.type) {
                InputType.FORWARD -> horizontal += it.value
                InputType.DOWN -> depth += it.value
                InputType.UP -> depth -= it.value
            }
        }

        return horizontal * depth
    }

    fun part2(input: List<InputData>): Int {
        var horizontal = 0
        var depth = 0
        var aim = 0

        input.forEach {
            when (it.type) {
                InputType.FORWARD -> {
                    horizontal += it.value
                    depth += it.value * aim
                }
                InputType.DOWN -> aim += it.value
                InputType.UP -> aim -= it.value
            }
        }

        return horizontal * depth
    }

    fun transform(input: List<List<String>>): List<InputData> = input
        .map { InputData(InputType.valueOf(it[0].uppercase()), it[1].toInt()) }

    listOf("Day02-test", "Day02-data")
        .map(::readComplexInput)
        .map(::transform)
        .onEach {
            println(part1(it)) // 150
            println(part2(it)) // 900
        }
}

private enum class InputType {
    FORWARD, DOWN, UP
}

private data class InputData(
    val type: InputType,
    val value: Int
)
