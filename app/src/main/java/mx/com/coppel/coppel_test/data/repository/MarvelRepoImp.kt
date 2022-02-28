package mx.com.coppel.coppel_test.data.repository

import android.util.Log
import mx.com.coppel.coppel_test.data.model.CharactersResponse
import mx.com.coppel.coppel_test.util.StringUtil
import mx.com.coppel.coppel_test.util.TimeUtil

/* 
 * @author azapata 
 * Feb 2022
*/
class MarvelRepoImp(
    private val marvelService: MarvelService
) : MarvelRepo {

    override suspend fun getCharacters(
        offset: Int,
        limit: Int,
        authPublicToken: String,
        authPrivateToken: String
    ): CharactersResponse {

        val timeStamp = TimeUtil.getTimeStamp()
        val hash = StringUtil.getHash(
            timeStamp = timeStamp,
            publicKey = authPublicToken,
            privateKey = authPrivateToken
        )

        return marvelService.getCharacters(
            apikey = authPublicToken,
            ts = timeStamp.toString(),
            hash = hash,
            limit = limit,
            offset = offset
        )
    }
}