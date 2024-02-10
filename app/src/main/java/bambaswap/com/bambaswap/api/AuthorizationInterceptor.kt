package bambaswap.com.bambaswap.api

import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber


class AuthorizationInterceptor : Interceptor {

    var token: String? = "sfshdjshfdvxtwyehwiueygwbamvcaf"
    var type: String? = null

    override fun intercept(chain: Interceptor.Chain): Response {

        val currentToken = token

        val request = if (currentToken != null) {
            Timber.d("Refresh addHeader")
            chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $currentToken")
                .build()
        } else {
            Timber.d("Refresh token chain request")
            chain.request()
        }

        return chain.proceed(request)
    }
}