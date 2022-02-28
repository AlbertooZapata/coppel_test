package mx.com.coppel.coppel_test.data.repository

import mx.com.coppel.coppel_test.data.model.CharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

/* 
 * @author azapata 
 * Feb 2022
*/
interface MarvelService {
    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apikey: String,
        @Query("ts") ts: String,
        @Query("hash") hash: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): CharactersResponse
}