package com.victorenache.cryptotracker.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CoinDto(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: String,
    @SerializedName("price_usd") val priceUsd: String,
    @SerializedName("percent_change_24h") val percentChange24h: String,
    @SerializedName("percent_change_7d") val percentChange7d: String,
    @SerializedName("market_cap_usd") val marketCapUsd: String,
    @SerializedName("volume24") val volumeUsd24h: String,

)

data class CoinResponseDto(
    val data: List<CoinDto>
)
