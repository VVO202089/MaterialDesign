package com.example.materialdesign.recyclerview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Notes(
    val id: String = "0",
    val name: String = "",
    val description: String = "",
) : SampleListItem, Parcelable