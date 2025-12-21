package com.victorenache.cryptotracker.domain.repositories

import com.victorenache.cryptotracker.core.Resource
import com.victorenache.cryptotracker.domain.model.Coin
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    fun getCoins(): Flow<Resource<List<Coin>>>
}