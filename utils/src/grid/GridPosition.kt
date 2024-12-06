package ua.olehlypskyi.adventofcode2025.utils.grid

@JvmInline
value class GridPosition internal constructor(private val values: IntArray) {
    constructor(row: Int, column: Int) : this(intArrayOf(row, column))

    var row: Int
        get() = values[0]
        set(value) {
            values[0] = value
        }

    var column: Int
        get() = values[1]
        set(value: Int) {
            values[1] = value
        }

    operator fun component1(): Int = row
    operator fun component2(): Int = column

    // --- Immutable operators ---

    operator fun plus(position: GridPosition): GridPosition = GridPosition(
        row = row + position.row,
        column = column + position.column
    )

    operator fun minus(position: GridPosition): GridPosition = GridPosition(
        row = row - position.row,
        column = column - position.column
    )

    operator fun times(scale: Int): GridPosition = GridPosition(
        row = row * scale,
        column = column * scale
    )

    fun copy(): GridPosition = GridPosition(
        values.copyOf()
    )

    fun rotated90(): GridPosition = GridPosition(
        row = -column,
        column = row
    )

    fun rotated180(): GridPosition = GridPosition(
        row = -row,
        column = -column
    )

    fun rotated270(): GridPosition = GridPosition(
        row = column,
        column = -row
    )

    // --- Mutable operators ---

    operator fun plusAssign(position: GridPosition) {
        row += position.row
        column += position.column
    }

    operator fun minusAssign(position: GridPosition) {
        row -= position.row
        column -= position.column
    }

    operator fun timesAssign(scale: Int) {
        row *= scale
        column *= scale
    }

    fun rotate90() {
        val row = this.row
        this.row = -column
        this.column = row
    }

    fun rotate180() {
        this.row = -row
        this.column = -column
    }

    fun rotate270() {
        val row = this.row
        this.row = column
        this.column = -row
    }


    override fun toString(): String = "[R$row;C$column]"


    companion object {
        val UP get() = GridPosition(-1, 0)
        val DOWN get() = GridPosition(1, 0)
        val LEFT get() = GridPosition(0, -1)
        val RIGHT get() = GridPosition(0, 1)
        val UPLEFT get() = GridPosition(-1, -1)
        val UPRIGHT get() = GridPosition(-1, 1)
        val DOWNLEFT get() = GridPosition(1, -1)
        val DOWNRIGHT get() = GridPosition(1, 1);
    }
}

fun IntArray.asGridPosition(): GridPosition = GridPosition(this)

