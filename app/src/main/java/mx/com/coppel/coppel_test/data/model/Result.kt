package mx.com.coppel.coppel_test.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/* 
 * @author azapata 
 * Feb 2022
*/
@Parcelize
data class Result(
    val id: Long,
    val name: String,
    val description: String,
    val thumbnail: Thumbnail,
    val comics: Comic,
    val series: Serie,
    val stories: Story
) : Parcelable
