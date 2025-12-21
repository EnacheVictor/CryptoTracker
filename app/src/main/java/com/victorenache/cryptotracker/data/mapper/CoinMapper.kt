package com.victorenache.cryptotracker.data.mapper

import com.victorenache.cryptotracker.data.remote.dto.CoinDto
import com.victorenache.cryptotracker.domain.model.Coin

fun CoinDto.toCoin(): Coin{
    return Coin(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDoubleOrNull() ?: 0.0,
        percentChange7d = percentChange7d.toDoubleOrNull() ?: 0.0,
        marketCapUsd = marketCapUsd.toDoubleOrNull() ?: 0.0,
        percentChange24h = percentChange24h.toDoubleOrNull() ?: 0.0,
        volumeUsd24h = volumeUsd24h.toDoubleOrNull() ?: 0.0,
    )
}