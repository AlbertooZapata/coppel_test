package mx.com.coppel.coppel_test.core

import okhttp3.Interceptor
import okhttp3.Response

/* 
 * @author azapata 
 * Feb 2022
*/
class SupportInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("staticHeader", "staticHeader_value")
            .build()
        return chain.proceed(request)
    }
}