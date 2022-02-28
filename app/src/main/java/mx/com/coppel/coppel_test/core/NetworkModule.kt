package mx.com.coppel.coppel_test.core

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.com.coppel.coppel_test.data.repository.MarvelService
import mx.com.coppel.coppel_test.util.StringUtil
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/* 
 * @author azapata 
 * Feb 2022
*/

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerRetrofit(okhttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(StringUtil.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(okhttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideMarvelService(retrofit: Retrofit): MarvelService {
        return retrofit.create(MarvelService::class.java)
    }

    @Singleton
    @Provides
    @Named("auth_public_token")
    fun providerAuthPublicToken(): String{
        return "38bfe4e8bc2155b35087d2d955ff8666"
    }

    @Singleton
    @Provides
    @Named("auth_private_token")
    fun providerAuthPrivateToken(): String{
        return "d3b7372cbacf1a3bf6c8b8ac95b5a24b8783a807"
    }

    @Provides
    @Singleton
    fun getUnsafeOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(SupportInterceptor())
        return builder.build()
    }
}