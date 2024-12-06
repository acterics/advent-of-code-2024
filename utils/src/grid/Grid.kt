package ua.olehlypskyi.adventofcode2025.utils.grid

interface Grid<T> {
    operator fun set(position: GridPosition, value: T)
    operator fun get(position: GridPosition): T
    fun containsPosition(position: GridPosition): Boolean
}

interface BaseGrid<T, Row>: Grid<T>, Collection<Row> {
    operator fun get(row: Int): Row
}