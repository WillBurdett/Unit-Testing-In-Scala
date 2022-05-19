package matchers

import com.h2.entities.Dollars
import com.h2.services.Currency
import org.scalatest.Inside

// trait 'Inside' allows us make statements about the nested object using pattern matching
class PatternMatchingSpec extends UnitSpec with Inside {

  behavior of "Currency when pattern matching"
  it should "be able to assert on the values inside of a currency" in {
    val tenUsd: Currency = "10 USD"
    // pass in the object to inside, then the pattern we are trying to extract (a currency)
    inside(tenUsd) { case Currency(code, amount, costInDollars) =>
      code should equal("USD")
      // '+-' is checking the range
      amount should equal(10.0 +- 0.5)
      costInDollars should be > Dollars(12)
    }
  }

  it should "be able to 'matchPattern' on the currency code" in {
    val tenUsd: Currency = "10 USD"
    tenUsd should matchPattern { case Currency("USD", _, _) =>
    }
  }
}
