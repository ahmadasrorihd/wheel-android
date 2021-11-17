package com.ahmadasrori.wheel.viewmodel

import com.ahmadasrori.data.Repository
import com.ahmadasrori.wheel.remote.Api
import io.reactivex.Scheduler
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import io.reactivex.schedulers.Schedulers

import io.reactivex.android.plugins.RxAndroidPlugins
import java.util.concurrent.Callable


@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var api: Api

    @Before
    fun setup() {
        viewModel = MainViewModel(Repository(api))
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun positiveCase() {
        viewModel.getRPM()
        val response = viewModel.apiResponse.value
        assertEquals("SPEED UPDATED", response)
    }

    @Test
    fun negativeCase() {
        viewModel.getRPM()
        val response = viewModel.apiResponse.value
        assertEquals("UNABLE TO UPDATE SPEED", response)
    }
}