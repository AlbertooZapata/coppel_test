package mx.com.coppel.coppel_test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/* 
 * @author azapata 
 * Feb 2022
*/
@Parcelize
data class Story(
    val available: Int,
    val items: List<Item>
) : Parcelable
