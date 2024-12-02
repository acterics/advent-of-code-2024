package ua.olehlypskyi.adventofcode2024.day2

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day2Tests {

    @Test
    fun `day 2 task 1 result should be expected`() {
        val task1Result = task1(File("input/task-1-test.txt"))
        val taskExpectedResult = 2
        assertEquals(taskExpectedResult, task1Result)
    }

}