package workflow.request

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import model.entity.CompanyAddress

@RunWith(Parameterized::class)
class CompanyAddressTest(val expectedValidation: Boolean,
                         val systemUnderTest: CompanyAddress) {

    @Test
    fun shouldValidateProperly() {
        val validation = systemUnderTest.isValid()

        Assertions.assertThat(validation).isEqualTo(expectedValidation)
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Collection<Array<Any>> {
            return listOf(
                    arrayOf(false, CompanyAddress("", "", "")),
                    arrayOf(false, CompanyAddress("1", "", "")),
                    arrayOf(false, CompanyAddress("1", "1", "")),
                    arrayOf(true, CompanyAddress("1", "1", "1"))
            )
        }
    }
}