package ru.owepkovr.freetoplaygames.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import freetoplaygames.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import ru.owepkovr.freetoplaygames.interactors.Api
import ru.owepkovr.freetoplaygames.interactors.NetworkRepository
import java.util.concurrent.TimeUnit
import org.koin.android.ext.koin.androidContext
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.owepkovr.freetoplaygames.arch.handler.ErrorMessageHandler
import ru.owepkovr.freetoplaygames.arch.network.NetworkErrorHandler
import ru.owepkovr.freetoplaygames.arch.network.NetworkErrorInterceptor

private const val WRITE_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val CONNECTION_TIMEOUT = 30L

private const val OKHTTP_QUALIFIER = "owepkovr_http_client"
private const val RETROFIT_QUALIFIER = "owepkovr_retrofit"

val networkModule = module {
    single { NetworkErrorHandler() }
    single { NetworkErrorInterceptor() }
    single(named(OKHTTP_QUALIFIER)) { provideOkHttpClient(androidContext(), get(), get()) }
    single(named(RETROFIT_QUALIFIER)) { provideRetrofit(get(named(OKHTTP_QUALIFIER)), BuildConfig.BASE_URL) }
    single { get<Retrofit>(named(RETROFIT_QUALIFIER)).create(Api::class.java) }
    single { NetworkRepository(get()) }
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.HEADERS
            }
        }
    }
    single { ErrorMessageHandler() }
}

private fun provideOkHttpClient(
    context: Context,
    loggingInterceptor: HttpLoggingInterceptor,
    networkErrorInterceptor: NetworkErrorInterceptor
) : OkHttpClient = OkHttpClient.Builder().apply {
    connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
    writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
    addInterceptor(loggingInterceptor)
    addInterceptor(ChuckerInterceptor(context))
    addInterceptor(headersInterceptor())
    addInterceptor(networkErrorInterceptor)
}.build()

private fun provideRetrofit(client: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(client)
        .addConverterFactory(createMoshiConverterFactory())
        .build()
}

private fun createMoshiConverterFactory(): MoshiConverterFactory {
    val moshi = Moshi.Builder()
        .build()
    return MoshiConverterFactory.create(moshi).asLenient()
}

private fun headersInterceptor() : Interceptor {
    return Interceptor { chain ->
        val builder = chain.request().newBuilder()

        builder
            .header("X-RapidAPI-Host", "free-to-play-games-database.p.rapidapi.com")
            .header("X-RapidAPI-Key", "4ca68f9c5dmsha15e2c465b91f1dp15ac60jsn45638855ef6e")

        return@Interceptor chain.proceed(builder.build())
    }
}

/*
private fun createGsonConverterFactory(): GsonConverter {
    return GsonConverterFactory.create()
}*/
