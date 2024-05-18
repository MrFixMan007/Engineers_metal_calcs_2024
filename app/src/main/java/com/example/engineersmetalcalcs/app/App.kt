package com.example.engineersmetalcalcs.app

import android.app.Application
import androidx.room.RoomDatabase
import com.example.engineersmetalcalcs.di.appModule
import com.example.engineersmetalcalcs.di.dataModule
import com.example.engineersmetalcalcs.di.domainModule
import data.model.CalcSave
import di.casting_menu_module
import di.core_uiModule
import di.feature_cargo_weight_module
import di.feature_saved_calcs_module
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.qualifier.named

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            printLogger()
            androidContext(this@App)
            modules(listOf(appModule, domainModule, dataModule, feature_cargo_weight_module, core_uiModule, casting_menu_module, feature_saved_calcs_module))
            get<RoomDatabase>()

            CoroutineScope(Dispatchers.IO).launch {
                get<List<CalcSave>>(named("calcSaves"))
            }

//            val db = get<RoomDatabase>()
//            CoroutineScope(Dispatchers.IO).launch {
//                db.clearAllTables()
//            }
        }
    }
}