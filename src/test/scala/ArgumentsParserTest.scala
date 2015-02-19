import org.danielwojda.alfrescofileloader.config.ArgumentsParser
import org.scalatest.{Matchers, FlatSpec}
import org.danielwojda.alfrescofileloader.config.Action._

class ArgumentsParserTest extends FlatSpec with Matchers{

  "Application" should "be in watch mode when -w arg is provided" in {
    val app = new ArgumentsParser(Array("-w"))
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
