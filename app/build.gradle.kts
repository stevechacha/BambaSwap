@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.navigation.safeargs)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.ksp)
}

android {
    namespace = "bambaswap.com.bambaswap"
    compileSdk = Integer.parseInt(libs.versions.compileSdk.get())

    defaultConfig {
        applicationId = "bambaswap.com.bambaswap"
        minSdk = Integer.parseInt(libs.versions.minSdk.get())
        targetSdk = Integer.parseInt(libs.versions.targetSdk.get())
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            isDebuggable = true
            multiDexEnabled = false
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isDebuggable = false
            multiDexEnabled = true
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    val appName = "BambaSwap"
    val newFileName = "$appName ${defaultConfig.versionName}"
    android.applicationVariants.all { variant ->
        variant.outputs.all { output ->
            val outputFileName = output.outputFile
            if (outputFileName != null) {
                val extension = outputFileName.name.substringAfterLast('.')
                if (extension == "apk" || extension == "aab") {
                    File(outputFileName.parent, "$newFileName.$extension")
                }
            }
            return@all true
        }
    }
}

    dependencies {

        implementation(libs.core.ktx)
        implementation(libs.appcompat)
        implementation(libs.material)
        implementation(libs.constraintlayout)
        testImplementation(libs.junit)
        androidTestImplementation(libs.androidx.test.ext.junit)
        androidTestImplementation(libs.espresso.core)

        implementation(libs.androidx.fragment.ktx)
        api(libs.navigation.fragment)
        api(libs.navigation.ui)

        implementation(libs.lifecycle.common)
        implementation(libs.lifecycle.livedata)
        implementation(libs.lifecycle.runtime)
        implementation(libs.lifecycle.viewmodel)

        implementation(libs.koin)
        implementation(libs.timber)
        implementation(libs.kotlin.coroutines.core)
        implementation(libs.kotlin.coroutines.android)

        implementation(libs.retrofit)
        implementation(libs.retrofit.moshi.converter)
        implementation(libs.logging.interceptor)

        implementation(libs.moshi)
        implementation(libs.moshi.adapters)
        ksp(libs.moshi.codegen)
        implementation(libs.gson)
        implementation(libs.browser)

        api(libs.room.ktx)
        api(libs.room.runtime)
        ksp(libs.room.compiler)
    }