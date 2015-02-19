package org.danielwojda.alfrescofileloader

import org.danielwojda.alfrescofileloader.config.{ArgumentsParser, Configuration}
import org.danielwojda.alfrescofileloader.config.Action._

object Main {

  def main(args: Array[String]) {
    
    val arguments = new ArgumentsParser(args)
    
    arguments.action match {
      case SHOW_HELP => HelpPage.show()
      case UPLOAD =>
        new Application(Configuration.load())
          .upload()
      case UPLOAD_AND_WATCH =>
        new Application(Configuration.load())
          .upload()
          .startWatching()
      case _ => HelpPage.show()
      
    }

  }
  
}
