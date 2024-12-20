package ua.olehlypskyi.adventofcode2024.day5

import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")

    val task2Result = task2(File("input/task-1.txt"))
    println("Task 2 Result: $task2Result")
}

fun task2(inputFile: File): Int {
    val input = parseInput(inputFile)
    val comparator = RuleComparator(input.rules)
    return input.pageNumbers
        .filter { pageNumbers ->
            !isPageNumbersCorrect(pageNumbers, rules = input.rules)
        }
        .map { pageNumbers ->
            pageNumbers.mapIndexed { index, value -> index to value }
                .sortedWith(comparator)
        }
        .sumOf { pageNumbers ->
            pageNumbers[pageNumbers.size / 2].second
        }
}

fun task1(inputFile: File): Int {
    val input = parseInput(inputFile)
    return input.pageNumbers
        .filter { pageNumbers ->
            isPageNumbersCorrect(pageNumbers, rules = input.rules)
        }
        .sumOf { pageNumbers ->
            pageNumbers[pageNumbers.size / 2]
        }
}

fun parseInput(input: File): TaskInput {
    var rulesLinesParsing: Boolean = true
    val rulesLines: MutableList<String> = mutableListOf()
    val pageNumbersLines: MutableList<String> = mutableListOf()
    input.readLines().forEach { line ->
        if (line.isBlank()) {
            rulesLinesParsing = false
            return@forEach
        }

        if (rulesLinesParsing) {
            rulesLines.add(line)
        } else {
            pageNumbersLines.add(line)
        }
    }
    val rules = parseRules(rulesLines)
    val pageNumbers = pageNumbersLines
        .filter { it.isNotEmpty() }
        .map { line ->
            line.split(",").map { it.toInt() }
        }
    return TaskInput(
        rules = rules,
        pageNumbers = pageNumbers
    )
}

fun parseRules(ruleLines: List<String>): Map<Int, List<RuleComparation>> {
    return ruleLines
        .filter { it.isNotEmpty() }
        .flatMap { ruleLine -> parseRule(ruleLine) }
        .groupBy { it.first }
        .mapValues { (_, pairs) -> pairs.map { it.second } }
}

fun parseRule(ruleText: String): List<Pair<Int, RuleComparation>> {
    val (left, right) = ruleText.split("|")
        .map { it.toInt() }
    return listOf(
        left to RuleComparation(right, isGreater = true),
        right to RuleComparation(left, isGreater = false)
    )
}

data class TaskInput(
    val rules: Map<Int, List<RuleComparation>>,
    val pageNumbers: List<List<Int>>
)

data class RuleComparation(
    val value: Int,
    val isGreater: Boolean
) {
    override fun toString(): String = "${if (isGreater) ">" else "<"}$value "
}

class RuleComparator(private val rules: Map<Int, List<RuleComparation>>) : Comparator<Pair<Int, Int>> {
    override fun compare(left: Pair<Int, Int>?, right: Pair<Int, Int>?): Int {
        val (leftIndex, leftValue) = left ?: return -1
        val (rightIndex, rightValue) = right ?: return 1

        val isGreater = rules[leftValue]
            ?.firstOrNull { rule -> rule.value == rightValue }
            ?.let { rule ->
                rule.isGreater == leftIndex > rightIndex
            }
            ?: true

        return if (isGreater) {
            1
        } else {
            -1
        }

    }

}

fun isPageNumbersCorrect(pageNumbers: List<Int>, rules: Map<Int, List<RuleComparation>>): Boolean {
    return (0 until pageNumbers.size - 1).all { leftIndex ->
        val left = pageNumbers[leftIndex]
        val leftRules = rules[left] ?: return@all true
        (leftIndex + 1 until pageNumbers.size).all inner@{ rightIndex ->
            val right = pageNumbers[rightIndex]

            leftRules
                .firstOrNull { rule -> rule.value == right }
                ?.isGreater ?: true
        }
    }
}