fun main() {
    println(listOf(1, 5, 2, 4, 3).sortedDescending().take(3))
    fun maxCals(input: String) =
        input.split("\n\n").map { elf ->
            elf.split("\n").map { it.toInt() }
        }.map { it.sum() }
            .max()

    fun part1(input: String): Int {
        return maxCals(input)
    }

    fun part2(input: String) =
        input.split("\n\n").map { elf ->
            elf.split("\n").map { it.toInt() }
        }.map { it.sum() }
            .sortedDescending().take(3)
            .sum()


    // test if implementation meets criteria from the description, like:
    val testInput = readText("Day01_test")
    check(part1(testInput) == 24000)

    val input = readText("Day01")
    println(part1(input))
    println(part2(input))
}
