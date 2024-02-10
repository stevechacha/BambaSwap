package bambaswap.com.bambaswap.di

import bambaswap.com.bambaswap.util.Environment
import org.koin.dsl.module

val environmentModule = module {
    single<Environment> { Environment.Dev }
}