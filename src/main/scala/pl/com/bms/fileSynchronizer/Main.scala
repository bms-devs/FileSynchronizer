package pl.com.bms.fileSynchronizer

import pl.com.bms.fileSynchronizer.config.Action._
import pl.com.bms.fileSynchronizer.config.{Action, ArgumentsParser, Configuration}

object Main {

  def main(args: Array[String]) {
    
    val arguments = new ArgumentsParser(args)
    
    arguments.action match {
      case SHOW_HELP => HelpPage.show()
      case UPLOAD =>
        new Application(Configuration.load(arguments.configFileName))
          .upload()
      case UPLOAD_AND_WATCH =>
        new Application(Configuration.load(arguments.configFileName))
          .upload()
          .startWatching()
      case WATCH =>
        new Application(Configuration.load(arguments.configFileName))
          .startWatching()
      case _ => HelpPage.show()
      
    }

  }
  
}
