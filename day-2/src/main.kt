package ua.olehlypskyi.adventofcode2024.day2

import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")

    val task2Result = task2(File("input/task-1.txt"))
    println("Task 2 Result: $task2Result")
}

fun task1(inputFile: File): Int {
    return parseFile(inputFile)
        .map { row ->
            isReportSafe(report = row)
        }
        .filter { it }
        .size
}

fun task2(inputFile: File): Int {
    return parseFile(inputFile)
        .mapNotNull { row ->
            applyProblemDampener(report = row)
        }
        .size
}

private fun applyProblemDampener(report: List<Int>): List<Int>? {
    val unsafeIndex = getUnsafeDiffIndex(report) ?: return report

    val possibleExcludeIndices = listOf(unsafeIndex, unsafeIndex - 1, unsafeIndex + 1)
        .filter { index -> report.indices.contains(index) }

    possibleExcludeIndices.forEach { excludeIndex ->
        val reportWithoutExcludedIndex = report.filterIndexed { index, _ ->
            index != excludeIndex
        }
        if (isReportSafe(reportWithoutExcludedIndex)) {
            return reportWithoutExcludedIndex
        }
    }
    return null
}

private fun getUnsafeDiffIndex(report: List<Int>): Int? {
    if (report.size < 2) return null
    val isIncreasing = report[0] < report[1]
    (1 until report.size).forEach { index ->
        val previousLevel = report[index - 1]
        val level = report[index]
        val isSafe = isDiffSafe(
            previousLevel = previousLevel,
            level = level,
            isIncreasing = isIncreasing
        )
        if (!isSafe) return index - 1
    }
    return null
}

private fun isReportSafe(report: List<Int>): Boolean {
    if (report.size < 2) return true
    val isIncreasing = report[0] < report[1]
    report.drop(1).fold(report[0]) { previousLevel, level ->
        val isSafe = isDiffSafe(
            previousLevel = previousLevel,
            level = level,
            isIncreasing = isIncreasing
        )

        if (!isSafe) return false

        level
    }
    return true
}

private fun isDiffSafe(previousLevel: Int, level: Int, isIncreasing: Boolean): Boolean {
    val maxDiff = 3
    val minDiff = 1

    val diff = level - previousLevel

    if (diff == 0) return false

    val isSafe = if (isIncreasing) {
        (minDiff..maxDiff).contains(diff)
    } else {
        (-maxDiff..-minDiff).contains(diff)
    }

    return isSafe
}

private fun parseFile(file: File): List<List<Int>> = file.readLines()
    .map { line ->
        line.split(Regex("\\s+")).map { it.toInt() }
    }