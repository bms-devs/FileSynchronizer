package pl.com.bms.fileSynchronizer

import org.scalatest.{FlatSpec, Matchers}
import pl.com.bms.fileSynchronizer.config.Action._
import pl.com.bms.fileSynchronizer.config.ArgumentsParser

class ArgumentsParserTest extends FlatSpec with Matchers{

  "Application" should "be in watch mode when -w arg is provided" in {
    val app = new ArgumentsParser(Array("-w"))
    app.action shouldBe WATCH
  }

  "Application" should "be in upload and watch mode when -W arg is provided" in {
    val app = new ArgumentsParser(Array("-W"))
    app.action shouldBe UPLOAD_AND_WATCH
  }
  
  it should "display help page when -h arg is provided" in {
    val app = new ArgumentsParser(Array("-h"))
    app.action shouldBe SHOW_HELP
  }  
  
  it should "display help page when --help arg is provided" in {
    val app = new ArgumentsParser(Array("--help"))
    app.action shouldBe SHOW_HELP
  }
  
  it should "set action as upload when no arg" in {
    val app = new ArgumentsParser(Array(""))
    app.action shouldBe UPLOAD
  }

}
