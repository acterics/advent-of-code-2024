package ua.olehlypskyi.adventofcode2024.day1

import java.io.File
import kotlin.math.abs

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")
}

fun task1(inputFile: File): Int {
    return parseLines(inputFile.readLines())
        .let { parsedRows ->
            val leftColumn = parsedRows.map { it[0] }
            val rightColumn = parsedRows.map { it[1] }
            leftColumn.sorted()
                .zip(rightColumn.sorted())
                .sumOf { (left, right) -> abs(left - right) }
        }
}

private typealias ParsedLines = List<List<Int>>
private fun parseLines(lines: List<String>): ParsedLines =
    lines.map { line ->
        line.split(Regex("\\s+"))
            .map {
                it.trim().toInt()
            }
    }