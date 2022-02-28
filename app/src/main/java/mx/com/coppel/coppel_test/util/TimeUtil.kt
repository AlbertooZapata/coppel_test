package mx.com.coppel.coppel_test.util

/* 
 * @author azapata 
 * Feb 2022
*/
class TimeUtil {
    companion object {

        fun getTimeStamp(): Long {
            return System.currentTimeMillis()
        }
    }
}