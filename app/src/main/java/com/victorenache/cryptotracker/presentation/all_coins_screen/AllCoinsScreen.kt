package com.victorenache.cryptotracker.presentation.all_coins_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorenache.cryptotracker.presentation.components.AllCoinsItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.victorenache.cryptotracker.ui.theme.BlackScreen
import com.victorenache.cryptotracker.ui.theme.WhiteDivider

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllCoinsScreen(
    viewModel: AllCoinsViewModel = hiltViewModel()
){
    val state = viewModel.state.value

    Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = BlackScreen,
            contentWindowInsets = WindowInsets(0.dp)
    ) {paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()

        )
        {

            LazyColumn(modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(top = 40.dp, bottom = 40.dp)) {

                item {
                    HorizontalDivider(
                        thickness = 0.7.dp,
                        color = WhiteDivider
                    )
                }
                items(state.coins)
                { coin ->
                    AllCoinsItem(coin = coin)
                    HorizontalDivider(
                        thickness = 0.7.dp,
                        color = WhiteDivider
                    )
                }
            }
            if(state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}