package bambaswap.com.bambaswap.di

import org.koin.dsl.module
import retrofit2.converter.moshi.MoshiConverterFactory

val networkModule = module {
    single { MoshiConverterFactory.create(get()).asLenient() }
}