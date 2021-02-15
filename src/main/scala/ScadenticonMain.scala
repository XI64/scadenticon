package hyp.imd

import core.logik


object ScadenticonMain {
  def main(args: Array[String]): Unit = {
    val newIden = logik.genIden(prop.settings.username)
    newIden.createImage()
    newIden.saveImage(prop.settings.filename)
  }
}