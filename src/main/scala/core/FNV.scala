package hyp.imd
package core

object FNV {
  //FNV-1a to generate the hash
  private val OFFSET_BASIS = BigInt("cbf29ce484222325", 16)
  private val FNVPRIME = BigInt("100000001b3", 16)
  private val MOD = BigInt(2).pow(64)
  private val MASK = 0xff

  @inline private final def calc(OFFSET_BASIS: BigInt , iuser : Byte) : BigInt = (OFFSET_BASIS ^ ( iuser & MASK) * FNVPRIME) % MOD
  @inline final def fnvOneA(username : Array[Byte]) : BigInt = username.foldLeft(OFFSET_BASIS)(calc)
}
