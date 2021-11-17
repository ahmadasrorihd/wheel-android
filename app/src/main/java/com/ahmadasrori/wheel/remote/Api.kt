package com.ahmadasrori.wheel.remote

import com.ahmadasrori.wheel.model.MainModel
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("csrng/csrng.php?min=0&max=100")
    fun getRPM(
    ): Observable<List<MainModel>>
}

