package bambaswap.com.bambaswap

import android.app.Application
import bambaswap.com.bambaswap.di.apiModule
import bambaswap.com.bambaswap.di.environmentModule
import bambaswap.com.bambaswap.di.networkModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BambaSwapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }


        startKoin {
            androidContext(androidContext = this@BambaSwapApplication)
            modules(
                apiModule,
                environmentModule,
                networkModule
            )
        }
    }
}