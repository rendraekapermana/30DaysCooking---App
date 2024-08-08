package com.example.a30dayscooking.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Food(
    @StringRes val foodRes: Int,
    @StringRes val receiptRes: Int,
    @DrawableRes val imageRes: Int
)

data class Profile(
    @StringRes val nameRes: Int,
    @StringRes val uploadRes: Int,
    @DrawableRes val profileRes: Int
)