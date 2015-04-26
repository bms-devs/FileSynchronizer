package pl.com.bms.fileSynchronizer.config

import org.scalatest.{FlatSpec, Matchers}

class ConfigurationTest extends FlatSpec with Matchers {

  "Configuration" should "detect Alfresco destination" in {
    val configuration = Configuration(
      Connection("","",""),
      "",
      "workspace://SpacesStore/17855f47-ef83-4eeb-b9f0-a41f07ab6cac",
      List(),
      List()
    )
    configuration.hasAlfrescoDestination shouldBe true
  }

  it should "detect ssh destination" in {
    val configuration = Configuration(
      Connection("","",""),
      "",
      "/home/user/folder",
      List(),
      List()
    )
    configuration.hasAlfrescoDestination shouldBe false
  }

}
