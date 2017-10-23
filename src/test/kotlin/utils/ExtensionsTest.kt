package utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class ExtensionsTest {

    @Test
    fun testActionableNotEmptyOnNotEmptyString() {
        var expectedOutcome = ""
        val certainString = "a"

        certainString.actionableNotEmpty({ expectedOutcome = "not empty" }, { expectedOutcome = "empty" })

        assertThat(expectedOutcome).isEqualTo("not empty")
    }

    @Test
    fun testActionableNotEmptyOnEmptyString() {
        var expectedOutcome = ""
        val certainString = ""

        certainString.actionableNotEmpty({ expectedOutcome = "not empty" }, { expectedOutcome = "empty" })

        assertThat(expectedOutcome).isEqualTo("empty")
    }

    @Test
    fun actionableNotEmptyShouldReturnValue() {
        val expectedOutcome = "not empty invoked"
        val certainString = "aaaaa"

        val output = certainString.actionableNotEmpty({ "not empty invoked" }, { "empty invoked" })

        assertThat(output).isEqualTo(expectedOutcome)
    }

    @Test
    fun actionableEmptyShouldReturnValue() {
        val expectedOutcome = "empty invoked"
        val certainString = ""

        val output = certainString.actionableNotEmpty({ "not empty invoked" }, { "empty invoked" })

        assertThat(output).isEqualTo(expectedOutcome)
    }
}