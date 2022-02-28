package mx.com.coppel.coppel_test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/* 
 * @author azapata 
 * Feb 2022
*/
@Parcelize
data class Data(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<Result>
) : Parcelable
