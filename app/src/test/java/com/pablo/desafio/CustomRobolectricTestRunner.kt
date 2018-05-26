package com.pablo.desafio

import com.example.pablo.desafio.BuildConfig
import org.junit.runners.model.InitializationError
import org.robolectric.RobolectricTestRunner

class CustomRobolectricTestRunner @Throws(InitializationError::class)
constructor(testClass: Class<*>) : RobolectricTestRunner(testClass) {
    init {
        var buildVariant = BuildConfig.BUILD_TYPE
        if (!BuildConfig.FLAVOR.isEmpty()) {
            buildVariant = buildVariant + "/" + BuildConfig.FLAVOR
        }

        System.setProperty("android.package", BuildConfig.APPLICATION_ID)
        System.setProperty("android.manifest", "build/intermediates/manifests/full/\$buildVariant/AndroidManifest.xml")
        System.setProperty("android.resources", "build/intermediates/res/$buildVariant")
        System.setProperty("android.assets", "build/intermediates/assets/$buildVariant")
        System.setProperty("sdk", "build/intermediates/assets/$buildVariant")
    }
}