package com.ahmadasrori.wheel.di

import com.ahmadasrori.data.Repository
import com.ahmadasrori.wheel.remote.RetrofitClient
import org.koin.dsl.module

val dataModule = module {
    single {
        RetrofitClient.instance
    }
    factory {
        Repository(get())
    }
}