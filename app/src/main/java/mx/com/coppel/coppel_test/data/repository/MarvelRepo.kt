package mx.com.coppel.coppel_test.data.repository

import mx.com.coppel.coppel_test.data.model.CharactersResponse

/* 
 * @author azapata 
 * Feb 2022
*/
interface MarvelRepo {
    suspend fun getCharacters(
        offset: Int,
        limit: Int,
        authPublicToken: String,
        authPrivateToken: String
    ): CharactersResponse
}