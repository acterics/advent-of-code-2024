package ua.olehlypskyi.adventofcode2024.day4

import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")
}

typealias Grid = List<CharArray>
typealias Position = IntArray

val target = listOf('X', 'M', 'A', 'S')
val directions: List<Position> = listOf(-1, 0, 1).flatMap { dx ->
    listOf(-1, 0, 1).map { dy -> intArrayOf(dx, dy) }
}

fun task1(input: File): Int {
    val grid: Grid = input.readLines().map { line -> line.toCharArray() }

    return grid.indices.sumOf { rowIndex ->
        val row = grid[rowIndex]
        row.indices.sumOf { columnIndex ->
            val position = intArrayOf(columnIndex, rowIndex)
            grid.targetMatchCount(position)
        }
    }
}

private fun Grid.targetMatchCount(
    position: Position
): Int {
    val element = this[position]
    if (element != target[0]) return 0

    val possibleDirections = directions.filter { direction ->
        checkDirection(
            position = position,
            direction = direction,
            targetIndex = 1
        )
    }


    return possibleDirections
        .filter { direction ->
            (2 until target.size).all { targetIndex ->
                checkDirection(
                    position = position,
                    direction = direction,
                    targetIndex = targetIndex
                )
            }
        }
        .size
}

private fun Grid.checkDirection(
    position: Position,
    direction: Position,
    targetIndex: Int
): Boolean {
    val nextPosition = position.apply(
        direction = direction,
        count = targetIndex
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

