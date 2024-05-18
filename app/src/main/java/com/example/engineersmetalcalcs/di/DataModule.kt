package com.example.engineersmetalcalcs.di

import androidx.room.RoomDatabase
import data.CalcSaveRepository
import data.model.CalcSave
import db.AppDatabase
import kotlinx.coroutines.runBlocking
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    single <RoomDatabase>{ AppDatabase.getInstance(get()) }
    factory <CalcSaveRepository> { AppDatabase.getInstance(get()) }

    factory <List<CalcSave>>(named("calcSaves")){
        runBlocking {
            get<CalcSaveRepository>().getAllCalcSaves()
        }
    }
}