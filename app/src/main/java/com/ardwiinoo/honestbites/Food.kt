package com.ardwiinoo.honestbites

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Food(
    var name: String,
    var description: String,
    var photo: Int,
    var price: String
): Parcelable