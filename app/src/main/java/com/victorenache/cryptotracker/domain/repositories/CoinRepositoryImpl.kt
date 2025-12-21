package com.victorenache.cryptotracker.domain.repositories

import com.victorenache.cryptotracker.core.Resource
import com.victorenache.cryptotracker.data.mapper.toCoin
import com.victorenache.cryptotracker.data.remote.CoinApi
import com.victorenache.cryptotracker.domain.model.Coin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinApi
) : CoinRepository {

    override fun getCoins(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())

            val response = api.getCoins()
            val coins = response.data.map { it.toCoin() }

            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Eroare neașteptată de server"))
        } catch (e: IOException) {
            emit(Resource.Error("DEBUG_IO: ${e.toString()}"))
        }
    }
}