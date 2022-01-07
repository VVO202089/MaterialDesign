package com.example.materialdesign.domain

import com.google.gson.annotations.SerializedName

data class CameraData(
    @field:SerializedName("id") val id: Int?,
    @field:SerializedName("name") val name: String?,
    @field:SerializedName("rover_id") val rover_id: Int?,
    @field:SerializedName("full_name") val full_name: String?
)