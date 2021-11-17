package com.ahmadasrori.wheel.model

import com.google.gson.annotations.SerializedName

data class MainModel(
    @SerializedName("status")
    val status: String?,
    @SerializedName("min")
    val min: Int?,
    @SerializedName("max")
    val max: Int?,
    @SerializedName("random")
    val random: Int?,
)