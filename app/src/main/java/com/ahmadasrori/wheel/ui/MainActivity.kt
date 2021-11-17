package com.ahmadasrori.wheel.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ahmadasrori.wheel.R
import com.ahmadasrori.wheel.databinding.ActivityMainBinding
import com.ahmadasrori.wheel.util.ProgressDialogHelper
import com.ahmadasrori.wheel.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModel()
    private lateinit var progressDialogHelper: ProgressDialogHelper

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialogHelper = ProgressDialogHelper()
        setContentView(binding.root)
        initListener()
        observe()
    }

    private fun initListener() {
        binding.btnUpdateSpeed.setOnClickListener {
            val fm = this@MainActivity.supportFragmentManager
            progressDialogHelper.show(fm, "oke")
            binding.btnUpdateSpeed.isEnabled = false
            viewModel.getRPM()
        }
    }

    private fun observe() {
        viewModel.apiResponse.observe(this, {
            progressDialogHelper.dismiss()
            binding.btnUpdateSpeed.isEnabled = true
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.rpm.observe(this, {
            binding.tvSpeed.text = getString(R.string.rpm, it.random)
            rotateTheView(binding.ivRPM, it.random!!.toLong())
        })
    }

    private fun rotateTheView(view: View?, duration: Long) {
        val rotate = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotate.duration = 1000-duration //one rotate in milliseconds
        rotate.interpolator = LinearInterpolator() // for smooth rotation
        rotate.repeatCount = Animation.INFINITE
        view?.startAnimation(rotate)
    }
}