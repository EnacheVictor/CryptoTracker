package com.victorenache.cryptotracker.presentation.all_coins_screen

import com.victorenache.cryptotracker.domain.model.Coin

data class AllCoinsState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
