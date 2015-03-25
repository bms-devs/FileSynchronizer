package pl.com.bms.fileSynchronizer

import java.nio.file.{Paths, NoSuchFileException}

import org.scalatest.{Matchers, WordSpec}
import pl.com.bms.fileSynchronizer.config.DirectoryToLoad

class DirectoryToLoadTest extends WordSpec with Matchers {

  "DirectoryToLoad" when {
    "polled for list of files" should {
      "return an empty list for empty folder" in {
        //given
        val emptyDirectory = Paths.get("src/test/resources/emptyDirectory").toFile
        emptyDirectory.mkdirs()
        //when
        val directory = DirectoryToLoad("src/test/resources", "/emptyDirectory")
        //then
        directory.filesToLoad() shouldBe empty
        //clean up
        emptyDirectory.delete()
      }

      "throw an exception for non-existent folder" in {
        intercept[NoSuchFileException] {
          DirectoryToLoad("src/test/resources", "/do-not-exist").filesToLoad()
        }
      }

      "return a list of files and folders" in {
        //when
        val directory = DirectoryToLoad("src/test/resources", "/")
        //then
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
