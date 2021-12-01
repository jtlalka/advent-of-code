/** Day 1 */
fun main() {

    fun part1(input: List<Int>): Int = input
        .zipWithNext()
        .filter { it.first < it.second }
        .size

    fun part2(input: List<Int>): Int = input
        .windowed(3)
        .map { it.sum() }
        .zipWithNext()
        .filter { it.first < it.second }
        .size

    readIntInput("Day01-data").let {
        println(part1(it))
        println(part2(it))
    }
}
