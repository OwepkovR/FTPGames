package ru.owepkovr.freetoplaygames.arch.network

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ru.owepkovr.freetoplaygames.arch.network.NetworkErrorHandler.Companion.CODE_INTERNAL_ERROR
import ru.owepkovr.freetoplaygames.arch.network.NetworkErrorHandler.Companion.UNKNOWN_ERROR_CODE

class NetworkErrorInterceptor : Interceptor, KoinComponent {

    private val networkErrorHandler: NetworkErrorHandler by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val response = chain.proceed(chain.request())

            if (response.code == CODE_INTERNAL_ERROR) {
                networkErrorHandler.setNetworkError(response.code)
            }

            return response
        } catch (e: Exception) {
            networkErrorHandler.setNetworkError(UNKNOWN_ERROR_CODE)

            return Response.Builder()
                .request(chain.request())
                .code(UNKNOWN_ERROR_CODE)
                .message(e.message.toString())
                .build()
        }
    }
}