package com.victorenache.cryptotracker.domain.model

data class Coin(
    val id: String,
    val name: String,
    val symbol: String,
    val rank: Int,
    val priceUsd: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val percentChange30d: Double,
    val marketCapUsd: Double,
    val volumeUsd24h: Double,
)
