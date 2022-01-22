package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class RoverData(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("landing_date") val landing_date: String?,
    @SerializedName("launch_date") val launch_date: String?,
    @SerializedName("status") val status: String?
)