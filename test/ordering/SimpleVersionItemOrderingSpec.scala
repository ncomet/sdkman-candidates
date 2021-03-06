package ordering

import org.scalatest.{Matchers, WordSpec}
import rendering.VersionItem

class SimpleVersionItemOrderingSpec extends WordSpec with Matchers {
  "VersionItem ordering" should {
    "accurately sort versions by version string ascending" in new RegexStringComparison with VersionItemOrdering {

      val v1 = VersionItem("2.12.7")
      val v2 = VersionItem("2.11.7")
      val v3 = VersionItem("2.12.1")
      val v4 = VersionItem("2.11.8")
      val v5 = VersionItem("2.12.6")

      val versions = List(v1, v2, v3, v4, v5)

      versions.ascendingOrder shouldBe List(v2, v4, v3, v5, v1)
    }

    "accurately sort versions by version string descending" in new RegexStringComparison with VersionItemOrdering {

      val v1 = VersionItem("2.12.7")
      val v2 = VersionItem("2.11.7")
      val v3 = VersionItem("2.12.1")
      val v4 = VersionItem("2.11.8")
      val v5 = VersionItem("2.12.6")

      val versions = List(v1, v2, v3, v4, v5)

      versions.descendingOrder shouldBe List(v1, v5, v3, v4, v2)
    }

    "accurately sort 10 and 9 version ranges" in new RegexStringComparison with VersionItemOrdering {
      val v1 = VersionItem("10.0.0-oracle")
      val v2 = VersionItem("1.0.0-graal")
      val v3 = VersionItem("9.0.0-zulu")

      val versions = List(v1, v2, v3)

      versions.ascendingOrder shouldBe List(v2, v3, v1)
    }

    "accurately sort java vendors" in new RegexStringComparison with VersionItemOrdering {
      val v1 = VersionItem("10.0.1-zulu")
      val v2 = VersionItem("10.0.1-openjdk")
      val v3 = VersionItem("10.0.1-oracle")

      val versions = List(v1, v2, v3)

      versions.ascendingOrder shouldBe List(v2, v3, v1)
    }
  }
}
