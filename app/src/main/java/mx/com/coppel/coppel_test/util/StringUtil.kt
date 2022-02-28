package mx.com.coppel.coppel_test.util

import java.math.BigInteger
import java.security.MessageDigest

/* 
 * @author azapata 
 * Feb 2022
*/
class StringUtil {
    companion object {
        const val BASE_URL = "https://gateway.marvel.com:443/"
        const val RESPONSE_TOTAL_ITEMS = 20

        private fun md5(input: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray()))
                .toString(16).padStart(32, '0')
        }

        fun getHash(timeStamp: Long, publicKey: String, privateKey: String): String {
            return md5(timeStamp.toString() + privateKey + publicKey)
        }
    }
}