package ua.olehlypskyi.adventofcode2025.utils.grid

import java.io.File

@JvmInline
value class CharGrid internal constructor(private val gridValues: Array<CharArray>) : BaseGrid<Char, CharArray> {
    companion object;

    override fun get(row: Int): CharArray {
        return gridValues[row]
    }

    override fun get(position: GridPosition): Char {
        return gridValues[position.row][position.column]
    }

    override val size: Int get() = gridValues.size

    override fun containsPosition(position: GridPosition): Boolean {
        return gridValues.indices.contains(position.row) &&
                gridValues[position.row].indices.contains(position.column)
    }

    override fun containsAll(elements: Collection<CharArray>): Boolean {
        return elements.all { element ->
            contains(element)
        }
    }

    override fun contains(element: CharArray): Boolean {
        return gridValues.any { row ->
            if (row.size != element.size) return@any false
            val rowIterator = row.iterator()
            val elementIterator = element.iterator()
            while (rowIterator.hasNext()) {
                if (rowIterator.next() != elementIterator.next()) return@any false
            }
            true
        }
    }

    override fun isEmpty(): Boolean = gridValues.isEmpty()

    override fun set(position: GridPosition, value: Char) {
        gridValues[position.row][position.column] = value
    }

    override fun iterator(): Iterator<CharArray> {
        return gridValues.iterator()
    }

    override fun toString(): String {
        val maxRowIndexSize = gridValues.indices.last.toString().length

        val maxColumnIndex = gridValues.maxBy { row -> row.size }.indices.last
        val maxColumnIndexSize = maxColumnIndex.toString().length.let { index ->
            if (index % 2 == 0) {
                index + 1
            } else {
                index
            }
        }

        return buildString {
            (0 .. maxColumnIndex)
                .joinToString("") { columnIndex ->
                    val columnIndexString = columnIndex.toString()
                    val sizeDiff = maxColumnIndexSize - columnIndexString.length
                    val leftSpaces = (0..(sizeDiff.floorDiv(2))).joinToString("") { " " }
                    val rightSpaces = (0..(sizeDiff.floorDiv(2))).joinToString("") { " " }
                    "$leftSpaces$columnIndexString$rightSpaces"
                }
                .also {
                    append((0 .. maxRowIndexSize).joinToString("") { " " })
                    appendLine(it)
                }

            gridValues.indices
                .joinToString("\n") { rowIndex ->
                    val rowIndexString = rowIndex.toString()
                    val spaces = (1..(maxRowIndexSize - rowIndexString.length) + 1).joinToString("") { " " }
                    val valuesString = gridValues[rowIndex].joinToString("") { value ->
                        val sizeDiff = (maxColumnIndexSize - 1)
                        val leftSpaces = (0..(sizeDiff.floorDiv(2))).joinToString("") { " " }
                        val rightSpaces = (0..(sizeDiff.floorDiv(2))).joinToString("") { " " }
                        "$leftSpaces$value$rightSpaces"
                    }
                    "$rowIndex$spaces$valuesString"
                }
                .also { appendLine(it) }
        }
    }
}

fun Array<CharArray>.asGrid(): CharGrid = CharGrid(this)

fun CharGrid.Companion.parse(file: File): CharGrid = file.readLines()
    .map { it.toCharArray() }
    .toTypedArray()
    .let { gridValues -> CharGrid(gridValues) }