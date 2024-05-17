package com.example.engineersmetalcalcs.di

import androidx.room.RoomDatabase
import data.CalcSaveRepository
import db.AppDatabase
import org.koin.dsl.module

val dataModule = module {
    single <RoomDatabase>{ AppDatabase.getInstance(get()) }
    factory <CalcSaveRepository> { AppDatabase.getInstance(get()) }
}