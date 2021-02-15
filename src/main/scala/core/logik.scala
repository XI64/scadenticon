package hyp.imd
package core

import java.awt.Color

object logik {

  //Dummy hashing- Better approaches can be implemented for hashing . Works for now

  def genHash(username : String) : String = {
    import java.math.BigInteger
    import java.security.MessageDigest
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(username.getBytes)
    val bigInt = new BigInteger(1, digest)
    val hashedString = bigInt.toString(16)
    hashedString
  }

  //TODO  Sequential Color Gen .Requires better spooling .

  def genColor(ragbad: List[Int]): Color = {
    import scala.util.Random

    val rand : Random = new Random(System.currentTimeMillis())
    val modelHsb = Color.RGBtoHSB(ragbad(rand.nextInt(5)), ragbad(rand.nextInt(5)), ragbad(rand.nextInt(5)),null)

    new Color(Color.HSBtoRGB(modelHsb(0),
      prop.settings.saturation, prop.settings.value))
  }

  def genIden(username : String) : Identicon = {

    //Convert hash string to integer
    val intSeq = genHash(username).toList.map(_.toInt)

    //Generate the transposed matrix with wrap . Use a parity mapper to generate the values with a keyhold of 15
    val pixelSet = intSeq.take(15).map( h => (h % 2) == 1).grouped(5).toList
      .transpose
      .map(i => i.reverse ::: i.tail)
    val color = genColor(intSeq.take(5))
    Identicon(pixelSet, color)
  }

}
