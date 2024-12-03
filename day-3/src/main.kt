package ua.olehlypskyi.adventofcode2024.day3

import java.io.File
import java.lang.IllegalArgumentException

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")

    val task2Result = task2(File("input/task-1.txt"))
    println("Task 2 Result: $task2Result")
}

val mulInstructionRegex = Regex("${Token.MUL}\\((0|[1-9][0-9]{0,2}),(0|[1-9][0-9]{0,2})\\)")
val doRegex = Regex("${Token.DO}\\(\\)")
val dontRegex = Regex("${Token.DONT}\\(\\)")

fun task1(inputFile: File): Int {
    val instructionRegex = mulInstructionRegex
    val input = inputFile.readText()

    return instructionRegex
        .findAll(input)
        .map { matchResult ->
            matchResult.groupValues
                .drop(1)
                .map { it.toInt() }
                .reduce(Int::times)
        }
        .sum()
}

fun task2(inputFile: File): Int {
    val instructionsRegex = Regex(
        listOf(
            dontRegex,
            doRegex,
            mulInstructionRegex
        ).joinToString("|") { it.pattern }
    )

    val input = inputFile.readText()

    return instructionsRegex.findAll(input)
        .map { matchResult -> Token(matchResult) }
        .fold(true to 0) { (isEnabled, sum), token ->
            when(token) {
                is Token.Do -> true to sum
                is Token.Dont -> false to sum
                is Token.Mul -> if (isEnabled) {
                    true to sum + token.args.reduce(Int::times)
                } else {
                    false to sum
                }
            }
        }
            .second

}


sealed class Token(val matchResult: MatchResult) {
    companion object {
        const val MUL = "mul"
        const val DO = "do"
        const val DONT = "don't"

        operator fun invoke(matchResult: MatchResult): Token = when {
            matchResult.value.startsWith(MUL) ->
                Mul(
                    matchResult = matchResult,
                    args = matchResult.groupValues.drop(1).map { it.toInt() }
                )
            matchResult.value.startsWith("$DONT()") -> Dont(matchResult)
            matchResult.value.startsWith("$DO()") -> Do(matchResult)
            else -> throw IllegalArgumentException("Unknown token ${matchResult.value}")
        }
    }

    class Mul(matchResult: MatchResult, val args: List<Int>) : Token(matchResult) {
        override fun toString(): String = "MUT(${args.joinToString(",")})"
    }
    class Do(matchResult: MatchResult) : Token(matchResult) {
        override fun toString(): String = "DO"
    }
    class Dont(matchResult: MatchResult) : Token(matchResult) {
        override fun toString(): String = "DON'T"
    }

}