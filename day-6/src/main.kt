package ua.olehlypskyi.adventofcode2024.day6

import ua.olehlypskyi.adventofcode2025.utils.grid.CharGrid
import ua.olehlypskyi.adventofcode2025.utils.grid.GridPosition
import ua.olehlypskyi.adventofcode2025.utils.grid.parse
import java.io.File

fun main() {
    val task1Result = task1(File("input/task-1.txt"))
    println("Task 1 Result: $task1Result")
}

fun task1(inputFile: File): Int {
    val grid = CharGrid.parse(inputFile)
    return estimateGuardPositions(grid).size
}

const val UP_DIRECTION = '^'
const val OBSTACLE = '#'

private fun estimateGuardPositions(grid: CharGrid): List<GridPosition> {
    val position = findStartPosition(grid)
    val direction = GridPosition.UP
    val guardPositions: MutableMap<Int, MutableSet<Int>> = mutableMapOf()
    while (grid.containsPosition(position)) {
        if (grid[position] == OBSTACLE) {
            position -= direction
            direction.rotate270()
            continue
        }
        guardPositions.getOrPut(position.row, { mutableSetOf() }).add(position.column)
        position += direction
    }

    return guardPositions.flatMap { (row, columns) ->
        columns.map { column -> GridPosition(row, column) }
    }
}

private fun findStartPosition(grid: CharGrid): GridPosition {
    grid.indices.forEach { row: Int ->
        grid[row].indices.forEach { column ->
            val position = GridPosition(row, column)
            if (grid[position] == UP_DIRECTION) {
                return position
            }
        }
    }
    throw IllegalArgumentException("Not found start $UP_DIRECTION in grid.\nGrid\n$grid")
}



