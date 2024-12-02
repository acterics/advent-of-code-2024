package ua.olehlypskyi.adventofcode2024.day2

import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")
}

fun task1(inputFile: File): Int {
    return parseFile(inputFile)
        .map { row ->
            isReportSafe(report = row)
        }
        .filter { it }
        .size
}

private fun isReportSafe(report: List<Int>): Boolean {
    val maxDiff = 3
    val minDiff = 1
    if (report.size < 2) return false
    val isIncreasing = report[0] < report[1]
    report.drop(1).fold(report[0]) { previousLevel, level ->
        val diff = level - previousLevel

        if (diff == 0) return false

        val isSafe = if (isIncreasing) {
            (minDiff..maxDiff).contains(diff)
        } else {
            (-maxDiff..-minDiff).contains(diff)
        }

        if (!isSafe) return false

        level
    }
    return true
}

private fun parseFile(file: File): List<List<Int>> = file.readLines()
    .map { line ->
        line.split(Regex("\\s+")).map { it.toInt() }
    }