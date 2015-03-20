package pl.com.bms.fileSynchronizer

import java.nio.file.NoSuchFileException

import org.scalatest.{Matchers, WordSpec}
import pl.com.bms.fileSynchronizer.config.DirectoryToLoad

class DirectoryToLoadTest extends WordSpec with Matchers {

  "DirectoryToLoad" when {
    "polled for list of files" should {
      "return an empty list for empty folder" in {
        val directory = DirectoryToLoad("src/test/resources", "/emptyDirectory")
        directory.filesToLoad() shouldBe empty
      }

      "throw an exception for non-existent folder" in {
        intercept[NoSuchFileException] {
          DirectoryToLoad("src/test/resources", "/do-not-exist").filesToLoad()
        }
      }

      "return a list of files and folders" in {
        val directory = DirectoryToLoad("src/test/resources", "/")
        directory.filesToLoad() should contain allOf (
            "testDirectory",
            "testDirectory/testDirectory",
            "testDirectory/testDirectory/testFile",
            "testDirectory/testFile"
            )
      }
    }
  }

}
