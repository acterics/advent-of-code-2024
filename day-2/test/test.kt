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

    @Test
    fun `day 2 task 2 test 1 result should be expected`() {
        val task2Result = task2(File("input/task-1-test.txt"))
        val taskExpectedResult = 4
        assertEquals(taskExpectedResult, task2Result)
    }

    @Test
    fun `day 2 task 2 test 2 result should be expected`() {
        val task2Result = task2(File("input/task-1-test-2.txt"))
        val taskExpectedResult = 4
        assertEquals(taskExpectedResult, task2Result)
    }

}