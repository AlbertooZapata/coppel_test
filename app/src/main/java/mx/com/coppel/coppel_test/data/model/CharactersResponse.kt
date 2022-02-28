package mx.com.coppel.coppel_test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/* 
 * @author azapata 
 * Feb 2022
*/
@Parcelize
data class CharactersResponse (
    val code: Int,
    val status: String,
    val data : Data
) : Parcelable