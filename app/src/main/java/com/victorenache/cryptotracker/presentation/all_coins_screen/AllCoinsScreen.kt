package com.victorenache.cryptotracker.presentation.all_coins_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorenache.cryptotracker.presentation.components.AllCoinsItem
import androidx.compose.foundation.lazy.items

@Composable
fun AllCoinsScreen(
    viewModel: AllCoinsViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn {
            items(state.coins) { coin ->
                AllCoinsItem(coin = coin)
            }
        }

        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}