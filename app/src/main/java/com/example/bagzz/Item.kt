package com.example.bagzz

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Item(
    @DrawableRes var image : Int,
    @StringRes var text : Int
)
