package com.fli.submission1

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Champions(
    val name: String,
    val description: String,
    val photo: Int
) : Parcelable
