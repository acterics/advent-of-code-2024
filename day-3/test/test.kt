package ua.olehlypskyi.adventofcode2024.day3

import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class Day3Tests {

    @Test
    fun `day 3 task 1 mul instruction regex invalid tests`() {
        val invalidInstructions = listOf(
            "mul(4*",
            "mul(6,9!",
            "?(12,34)",
            "mul ( 2 , 4 )",
            "mul(01,1)",
            "mul(1,01)",
            "mul(1000,1)",
            "mul(1,1000)",
        )

        invalidInstructions.forEach { instruction ->
            assertFalse(
                instruction.matches(mulInstructionRegex),
                "Instruction $instruction should not match instruction regex"
            )
        }

    }

    @Test
    fun `day 3 task 1 mul instruction regex valid tests`() {
        val validInstructions = listOf(
            "mul(44,46)",
            "mul(123,4)",
            "mul(999,999)",
            "mul(1,1)",
            "mul(0,1)",
            "mul(1,0)",
            "mul(0,0)",
        )

        validInstructions.forEach { instruction ->
            assertTrue(
                instruction.matches(mulInstructionRegex),
                "Instruction $instruction should match instruction regex"
            )
        }
    }

    @Test
    fun `day 3 task 1 result should be expected`() {
        val task1Result = task1(File("input/task-1-test.txt"))
        val taskExpectedResult = 161
        assertEquals(taskExpectedResult, task1Result)
    }
}