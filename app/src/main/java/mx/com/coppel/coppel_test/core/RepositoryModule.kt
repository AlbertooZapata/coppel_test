package mx.com.coppel.coppel_test.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.coppel.coppel_test.data.repository.MarvelRepo
import mx.com.coppel.coppel_test.data.repository.MarvelRepoImp
import mx.com.coppel.coppel_test.data.repository.MarvelService
import javax.inject.Singleton

/* 
 * @author azapata 
 * Feb 2022
*/
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMarvelRepo(
        marvelService: MarvelService
    ): MarvelRepo {
        return MarvelRepoImp(marvelService = marvelService)
    }
}