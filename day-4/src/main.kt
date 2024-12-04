package ua.olehlypskyi.adventofcode2024.day4

import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")

    val task2Result = task2(File("input/task-1.txt"))
    println("Task 2 Result $task2Result")
}

typealias Grid = List<CharArray>
typealias Position = IntArray


fun task2(input: File): Int {
    val grid: Grid = parseGrid(input)
    return grid.indices.sumOf { rowIndex ->
        val row = grid[rowIndex]
        row.indices.sumOf { columnIndex ->
            val position = intArrayOf(columnIndex, rowIndex)
            grid.task2TargetMatchesCount(position)
        }
    }
}

const val TASK_2_START = 'A'
val task2TargetVariants = listOf(
    listOf('M', 'S'),
    listOf('S', 'M')
)
val task2DirectionsVariants: List<List<Position>> = listOf(
    listOf(
        intArrayOf(-1, -1),
        intArrayOf(1, 1),
    ),
    listOf(
        intArrayOf(1, -1),
        intArrayOf(-1, 1)
    )
)

private fun Grid.task2TargetMatchesCount(
    position: Position
): Int {

    val element = this[position]
    if (element != TASK_2_START) return 0

    val isMatchTarget = task2DirectionsVariants.all { directions ->
        task2TargetVariants.any { target ->
            directions.indices.all { directionIndex ->
                checkDirection(
                    target = target,
                    position = position,
                    direction = directions[directionIndex],
                    targetIndex = directionIndex,
                    directionDepth = 1
                )
            }
        }
    }
    return if (isMatchTarget) {
        1
    } else {
        0
    }
}


fun task1(input: File): Int {
    val grid: Grid = parseGrid(input)
    return grid.indices.sumOf { rowIndex ->
        val row = grid[rowIndex]
        row.indices.sumOf { columnIndex ->
            val position = intArrayOf(columnIndex, rowIndex)
            grid.task1TargetMatchesCount(position)
        }
    }
}

val task1Target = listOf('X', 'M', 'A', 'S')
val task1Directions: List<Position> = listOf(-1, 0, 1).flatMap { dx ->
    listOf(-1, 0, 1).map { dy -> intArrayOf(dx, dy) }
}

private fun Grid.task1TargetMatchesCount(
    position: Position
): Int {
    val target = task1Target
    val directions = task1Directions
    val element = this[position]
    if (element != target[0]) return 0

    val possibleDirections = directions.filter { direction ->
        checkDirection(
            target = target,
            position = position,
            direction = direction,
            targetIndex = 1,
            directionDepth = 1
        )
    }
    return possibleDirections
        .filter { direction ->
            (2 until target.size).all { targetIndex ->
                checkDirection(
                    target = target,
                    position = position,
                    direction = direction,
                    targetIndex = targetIndex,
                    directionDepth = targetIndex
                )
            }
        }
        .size
}

private fun Grid.checkDirection(
    target: List<Char>,
    position: Position,
    direction: Position,
    targetIndex: Int,
    directionDepth: Int
): Boolean {
    val nextPosition = position.apply(
        direction = direction,
        count = directionDepth
    )
    if (!containsPosition(nextPosition)) {
        return false
    }
    val element = this[nextPosition]
    return element == target[targetIndex]
}


private operator fun Grid.get(position: Position): Char {
    return this[position[1]][position[0]]
}

private fun Position.apply(direction: Position, count: Int): Position {
    return intArrayOf(this[0] + direction[0] * count, this[1] + direction[1] * count)
}

private fun Grid.containsPosition(position: Position): Boolean {
    return indices.contains(position[1]) &&
            get(position[1]).indices.contains(position[0])
}

private fun parseGrid(file: File): Grid = file.readLines().map { line -> line.toCharArray() }
