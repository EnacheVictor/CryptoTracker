package com.victorenache.cryptotracker.domain.use_cases

import com.victorenache.cryptotracker.core.Resource
import com.victorenache.cryptotracker.domain.model.Coin
import com.victorenache.cryptotracker.domain.repositories.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
){
    operator fun invoke(): Flow<Resource<List<Coin>>>{
        return repository.getCoins()
    }
}