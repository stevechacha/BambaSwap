package bambaswap.com.bambaswap.di

import bambaswap.com.bambaswap.api.AuthorizationInterceptor
import bambaswap.com.bambaswap.api.BambaSwapApi
import bambaswap.com.bambaswap.util.Environment
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import java.util.concurrent.TimeUnit

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(get<Environment>().url)
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()
    }

    single {
        OkHttpClient.Builder().apply {
            readTimeout(90, TimeUnit.SECONDS)
            connectTimeout(90, TimeUnit.SECONDS)
            addInterceptor(get<AuthorizationInterceptor>())
            addInterceptor(get<HttpLoggingInterceptor>())
            getAll<Interceptor>()
                .forEach {
                    addInterceptor(it)
                }
        }.build()
    }

    single {
        Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .add(NullToEmptyStringAdapter)
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    single { get<Retrofit>().create(BambaSwapApi::class.java) }

    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    } bind Interceptor::class

    single { Dispatchers.IO }


    single { AuthorizationInterceptor() } bind Interceptor::class
}

// TODO: Some values in the api response are null which caused the app to crash
object NullToEmptyStringAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextString()
        }
        reader.nextNull<Unit>()
        return ""
    }
}