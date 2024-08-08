package com.example.a30dayscooking.data

import com.example.a30dayscooking.R
import com.example.a30dayscooking.model.Food
import com.example.a30dayscooking.model.Profile

object DataSource {
    val cuisine = listOf(
        Food(R.string.food1, R.string.receipt1, R.drawable.spaghetti_carbonara),
        Food(R.string.food2, R.string.receipt2, R.drawable.sushi),
        Food(R.string.food3, R.string.receipt3, R.drawable.chicken_tikka_masala),
        Food(R.string.food4, R.string.receipt4, R.drawable.pad_thai),
        Food(R.string.food5, R.string.receipt5, R.drawable.borscht)
    )

    val profile = listOf(
        Profile(R.string.profile1, R.string.upload1, R.drawable.sashfir),
        Profile(R.string.profile2, R.string.upload2, R.drawable.syifa_hadju),
        Profile(R.string.profile3, R.string.upload3, R.drawable.pevita_pearce),
        Profile(R.string.profile4, R.string.upload4, R.drawable.daenerys_targaryen),
        Profile(R.string.profile5, R.string.upload5, R.drawable.anselma_putri),

    )
}