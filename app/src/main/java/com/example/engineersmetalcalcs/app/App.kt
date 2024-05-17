package com.example.engineersmetalcalcs.app

import android.app.Application
import com.example.engineersmetalcalcs.di.appModule
import com.example.engineersmetalcalcs.di.domainModule
import di.casting_menu_module
import di.core_uiModule
import di.feature_cargo_weight_module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            printLogger()
            androidContext(this@App)
            modules(listOf(appModule, domainModule, feature_cargo_weight_module, core_uiModule, casting_menu_module))
        }
    }
}