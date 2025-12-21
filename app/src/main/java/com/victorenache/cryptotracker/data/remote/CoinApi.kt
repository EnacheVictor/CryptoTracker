package com.victorenache.cryptotracker.data.remote

import com.victorenache.cryptotracker.data.remote.dto.CoinResponseDto
import retrofit2.http.GET

interface CoinApi {
    @GET("api/tickers")
    suspend fun getCoins():CoinResponseDto

    companion object {
        const val BASE_URL = "https://api.coinlore.net/"
    }
}