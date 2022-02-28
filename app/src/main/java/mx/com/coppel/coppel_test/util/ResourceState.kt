package mx.com.coppel.coppel_test.util

import java.lang.Exception

/* 
 * @author azapata 
 * Feb 2022
*/
sealed class ResourceState<out T> {
    object Default : ResourceState<Nothing>()
    object Loading : ResourceState<Nothing>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Failure<out T>(val exception: Exception) : ResourceState<T>()
}