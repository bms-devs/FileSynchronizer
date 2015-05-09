package pl.com.bms.fileSynchronizer

import org.scalatest.{FlatSpec, Matchers}
import pl.com.bms.fileSynchronizer.config.Action._
import pl.com.bms.fileSynchronizer.config.ArgumentsParser

class ArgumentsParserTest extends FlatSpec with Matchers{

  "Application" should "be in watch mode when -w arg is provided" in {
    val app = new ArgumentsParser(Array("-w"))
    app.action shouldBe WATCH
  }

  it should "be in upload and watch mode when -W arg is provided" in {
    val app = new ArgumentsParser(Array("-W"))
    app.action shouldBe UPLOAD_AND_WATCH
  }
  
  it should "set action as upload when no arg" in {
    val app = new ArgumentsParser(Array())
    app.action shouldBe UPLOAD
  }

  it should "provide default config file name if not specified" in {
    val app = new ArgumentsParser(Array())
    app.configFileName shouldBe "config.json"
  }

  it should "expose provided config file name" in {
    val app = new ArgumentsParser(Array("-c", "file.json"))
    app.configFileName shouldBe "file.json"
  }

  it should "expose provided config file name (alternative --config option)" in {
    val app = new ArgumentsParser(Array("--config", "file.json"))
    app.configFileName shouldBe "file.json"
  }
}
