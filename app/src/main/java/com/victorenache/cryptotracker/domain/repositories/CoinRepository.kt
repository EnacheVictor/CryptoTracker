package com.victorenache.cryptotracker.domain.repositories

import com.victorenache.cryptotracker.domain.model.Coin

interface CoinRepository {
    suspend fun getCoins(): Result<List<Coin>>
}