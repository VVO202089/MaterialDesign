package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class RoverData(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("landing_date") val landing_date: String?,
    @field:SerializedName("launch_date") val launch_date: String?,
    @field:SerializedName("status") val status: String?
)