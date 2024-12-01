package ua.olehlypskyi.adventofcode2024.day1

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals


class Day1Tests {

    @Test
    fun `day 1 task 1 result should be expected`() {
        val task1Result = task1(File("input/task-1-test.txt"))
        val taskExpectedResult = 11
        assertEquals(taskExpectedResult, task1Result)
    }

    @Test
    fun `day 1 task 2 result should be expected`() {
        val task2Result = task2(File("input/task-1-test.txt"))
        val taskExpectedResult = 31
        assertEquals(taskExpectedResult, task2Result)
    }

}