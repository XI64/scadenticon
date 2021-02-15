package hyp.imd
package core

import java.awt.Color
import java.awt.image.BufferedImage

sealed case class Identicon(colorSet : List[List[Boolean]], c : Color) {

  //Generate image using BufferedImage and 2DGC
  def createImage() : BufferedImage = {
    val width = prop.settings.width
    val pic = new BufferedImage(width * 5 , width * 5, BufferedImage.TYPE_INT_RGB)
    val ggen = pic.createGraphics()

    ggen.setBackground(prop.settings.backgroundColor)
    ggen.fillRect(0,0,pic.getHeight(),pic.getWidth())
    ggen.setColor(c)


    // Filter on pixels and set coords

    val boardArg = colorSet.map {
      _.zipWithIndex
    }.zipWithIndex.flatMap { case (row, y) => row
      .filter { case (i, _) => i }
      .map { case (_, x) => (x, y) } }

    boardArg.foreach{case(x,y) => ggen.fillRect(x* width, y*width, width, width)}
    pic
  }

  //Image saver
  def saveImage(fileName : String): Boolean = {
    import java.io.File
    import javax.imageio.ImageIO
    try {
      ImageIO.write(createImage(), "png", new File(fileName + ".png"))
    } catch {
      case e : Exception =>
        throw new RuntimeException("Error on save", e)
    }
  }
}
