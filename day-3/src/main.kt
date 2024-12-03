package ua.olehlypskyi.adventofcode2024.day3

import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")
}
val mulInstructionRegex = Regex("mul\\((0|[1-9][0-9]{0,2}),(0|[1-9][0-9]{0,2})\\)")

fun task1(inputFile: File): Int {
    val instructionRegex = mulInstructionRegex
    val input = inputFile.readText()

    return instructionRegex
        .findAll(input)
        .map { matchResult ->
            matchResult.groupValues
                .drop(1)
                .map { it.toInt() }
                .reduce { result, value -> result * value }
        }
        .sum()
}