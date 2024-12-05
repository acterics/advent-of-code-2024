package ua.olehlypskyi.adventofcode2024.day5

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals

class Day5Tests {

    @Test
    fun `day 5 task 1 result should be expected`() {
        val task1Result = task1(File("input/task-1-test.txt"))
        val taskExpectedResult = 143
        assertEquals(taskExpectedResult, task1Result)
    }

    @Test
    fun `day 5 task 2 result should be expected`() {
        val task1Result = task2(File("input/task-1-test.txt"))
        val taskExpectedResult = 123
        assertEquals(taskExpectedResult, task1Result)
    }


}