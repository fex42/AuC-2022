import java.lang.IllegalStateException

enum class Thing { ROCK, PAPER, SCISOR }
enum class Result { WIN, LOOSE, DRAW }

fun result(player: Thing, opponent: Thing): Result =
    when (player) {
        Thing.ROCK -> when (opponent) {
            Thing.ROCK -> Result.DRAW
            Thing.PAPER -> Result.LOOSE
            Thing.SCISOR -> Result.WIN
        }

        Thing.PAPER -> when (opponent) {
            Thing.ROCK -> Result.WIN
            Thing.PAPER -> Result.DRAW
            Thing.SCISOR -> Result.LOOSE
        }

        Thing.SCISOR -> when (opponent) {
            Thing.ROCK -> Result.LOOSE
            Thing.PAPER -> Result.WIN
            Thing.SCISOR -> Result.DRAW
        }
    }

fun move(player: Thing, result: Result): Thing =
    when (player) {
        Thing.ROCK -> when (result) {
            Result.WIN -> Thing.PAPER
            Result.LOOSE -> Thing.SCISOR
            Result.DRAW -> Thing.ROCK
        }

        Thing.PAPER -> when (result) {
            Result.WIN -> Thing.SCISOR
            Result.LOOSE -> Thing.ROCK
            Result.DRAW -> Thing.PAPER
        }

        Thing.SCISOR -> when (result) {
            Result.WIN -> Thing.ROCK
            Result.LOOSE -> Thing.PAPER
            Result.DRAW -> Thing.SCISOR
        }
    }

fun String.toThing() = when (this) {
    "A" -> Thing.ROCK
    "B" -> Thing.PAPER
    "C" -> Thing.SCISOR
    "X" -> Thing.ROCK
    "Y" -> Thing.PAPER
    "Z" -> Thing.SCISOR
    else -> throw IllegalStateException("unknown: $this")
}

fun String.toResult() = when (this) {
    "X" -> Result.LOOSE
    "Y" -> Result.DRAW
    "Z" -> Result.WIN
    else -> throw IllegalStateException("unknown: $this")
}

fun Thing.points() = when (this) {
    Thing.ROCK -> 1
    Thing.PAPER -> 2
    Thing.SCISOR -> 3
}

fun Result.points() = when (this) {
    Result.WIN -> 6
    Result.LOOSE -> 0
    Result.DRAW -> 3
}

fun main() {
    fun part1(input: List<String>): Int {
        val l1 = input.map {
            it.split(" ").map(String::toThing)
        }.map { list -> Pair(list[0], list[1]) }

        return l1.map { move ->
            move.second.points() + result(move.second, move.first).points()
        }.sum()
    }

    fun part2(input: List<String>): Int {
        val r = input.map {
            it.split(" ")
        }.map {
            Pair(it[0].toThing(), it[1].toResult())
        }.map { (opponentThing, result) ->
            move(opponentThing, result).points() +
                    result.points()
        }.sum()
        return r
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
