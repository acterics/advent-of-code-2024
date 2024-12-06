package ua.olehlypskyi.adventofcode2025.utils.grid

import kotlin.test.Test
import kotlin.test.assertEquals

class GridPositionTests {

    @Test
    fun `rotate90 tests from UP`() {
        val position = GridPosition.UP

        position.rotate90()
        assertEquals(GridPosition.LEFT.row, position.row)
        assertEquals(GridPosition.LEFT.column, position.column)

        position.rotate90()
        assertEquals(GridPosition.DOWN.row, position.row)
        assertEquals(GridPosition.DOWN.column, position.column)

        position.rotate90()
        assertEquals(GridPosition.RIGHT.row, position.row)
        assertEquals(GridPosition.RIGHT.column, position.column)

        position.rotate90()
        assertEquals(GridPosition.UP.row, position.row)
        assertEquals(GridPosition.UP.column, position.column)
    }

    @Test
    fun `rotate90 tests from UPLEFT`() {
        val position = GridPosition.UPLEFT

        position.rotate90()
        assertEquals(GridPosition.DOWNLEFT.row, position.row)
        assertEquals(GridPosition.DOWNLEFT.column, position.column)

        position.rotate90()
        assertEquals(GridPosition.DOWNRIGHT.row, position.row)
        assertEquals(GridPosition.DOWNRIGHT.column, position.column)

        position.rotate90()
        assertEquals(GridPosition.UPRIGHT.row, position.row)
        assertEquals(GridPosition.UPRIGHT.column, position.column)

        position.rotate90()
        assertEquals(GridPosition.UPLEFT.row, position.row)
        assertEquals(GridPosition.UPLEFT.column, position.column)
    }

    @Test
    fun `rotate180 tests from UP`() {
        val position = GridPosition.UP

        position.rotate180()
        assertEquals(GridPosition.DOWN.row, position.row)
        assertEquals(GridPosition.DOWN.column, position.column)

        position.rotate180()
        assertEquals(GridPosition.UP.row, position.row)
        assertEquals(GridPosition.UP.column, position.column)
    }

    @Test
    fun `rotate180 tests from UPLEFT`() {
        val position = GridPosition.UPLEFT

        position.rotate180()
        assertEquals(GridPosition.DOWNRIGHT.row, position.row)
        assertEquals(GridPosition.DOWNRIGHT.column, position.column)

        position.rotate180()
        assertEquals(GridPosition.UPLEFT.row, position.row)
        assertEquals(GridPosition.UPLEFT.column, position.column)
    }

    @Test
    fun `rotate180 tests from UPRIGHT`() {
        val position = GridPosition.UPRIGHT

        position.rotate180()
        assertEquals(GridPosition.DOWNLEFT.row, position.row)
        assertEquals(GridPosition.DOWNLEFT.column, position.column)

        position.rotate180()
        assertEquals(GridPosition.UPRIGHT.row, position.row)
        assertEquals(GridPosition.UPRIGHT.column, position.column)
    }

}

