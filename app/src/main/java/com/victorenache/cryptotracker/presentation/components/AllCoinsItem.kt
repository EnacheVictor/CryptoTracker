package com.victorenache.cryptotracker.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorenache.cryptotracker.domain.model.Coin
import com.victorenache.cryptotracker.ui.theme.BlackScreen

@SuppressLint("DefaultLocale")
@Composable
fun AllCoinsItem(coin: Coin,
                 modifier: Modifier = Modifier) {

    val isPositive = coin.percentChange24h >= 0
    val trendColor = if (isPositive) Color(0xFF2AD232) else Color(0xFFFA1000)


    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = BlackScreen),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = coin.rank,
                modifier = Modifier.width(30.dp),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = coin.name,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis)
                Text(
                    text = coin.symbol,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Vol 24h",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
                Text(text = "$${formatAmount(coin.volumeUsd24h)}",
                    color = Color.White,
                    fontSize = 12.sp)
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "$${String.format("%.2f", coin.priceUsd)}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${coin.percentChange24h}%",
                    color = if (coin.percentChange24h >= 0) Color.Green else Color.Red,
                    fontSize = 12.sp
                )
            }
        }
    }
}
@SuppressLint("DefaultLocale")
fun formatAmount(amount: Double): String {
    return when {
        amount >= 1_000_000_000 -> String.format("%.1fB", amount / 1_000_000_000)
        amount >= 1_000_000 -> String.format("%.1fM", amount / 1_000_000)
        else -> String.format("%.0f", amount)
    }
}
