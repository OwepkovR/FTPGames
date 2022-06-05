package ru.owepkovr.freetoplaygames.arch.network

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class NetworkErrorInterceptor : Interceptor, KoinComponent {
    private val networkErrorHandler : NetworkErrorHandler by inject()

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())

        if (response.code == NetworkErrorHandler.CODE_INTERNAL_ERROR) {
            networkErrorHandler.setNetworkError(response.code)
        }

        return response
    }
}