package pl.com.bms.fileSynchronizer

import java.nio.file.NoSuchFileException

import org.scalatest.{BeforeAndAfter, Matchers, WordSpec}
import pl.com.bms.fileSynchronizer.config.DirectoryToLoad
import java.io.File

class DirectoryToLoadTest extends WordSpec with Matchers with BeforeAndAfter {

  val TEST_RESOURCES_BASE = "src/test/resources"
  val EMPTY_DIRECTORY_PATH = "/emptyDirectory"

  "DirectoryToLoad" when {
    "created on empty directory" should {
      before {
        new File(TEST_RESOURCES_BASE + EMPTY_DIRECTORY_PATH).mkdirs()
      }

      "return an empty list for empty folder" in {
        val directory = DirectoryToLoad(TEST_RESOURCES_BASE, EMPTY_DIRECTORY_PATH)
        directory.filesToLoad() shouldBe empty
      }
      after {
        new File(TEST_RESOURCES_BASE + EMPTY_DIRECTORY_PATH).delete()
      }
    }

    "created on non-existent directory" should {
      "throw an exception for non-existent folder" in {
        intercept[NoSuchFileException] {
          DirectoryToLoad(TEST_RESOURCES_BASE, "/do-not-exist").filesToLoad()
        }
      }
    }

    "created on non-empty directory" should {
      "return a deep list of files and folders" in {
        val directory = DirectoryToLoad(TEST_RESOURCES_BASE, "/")
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
