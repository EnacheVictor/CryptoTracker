package com.victorenache.cryptotracker.presentation.all_coins_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.victorenache.cryptotracker.presentation.components.AllCoinsItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemColors
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.victorenache.cryptotracker.presentation.components.MainTopBar
import com.victorenache.cryptotracker.ui.theme.BlackScreen
import com.victorenache.cryptotracker.ui.theme.WhiteDivider
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AllCoinsScreen(
    viewModel: AllCoinsViewModel = hiltViewModel()
){
    val state = viewModel.state.value
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(320.dp),
                drawerContainerColor = Color(0xFF1A1A1A),
                drawerContentColor = Color.White
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "CRYPTO MENU",
                    modifier = Modifier.padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(color = Color.Gray, thickness = 0.5.dp)

                NavigationDrawerItem(
                    label = { Text(text = "Market Live") },
                    selected = true,
                    onClick = {
                        scope.launch { drawerState.close() }
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color(0xFF333333),
                    )
                )
                NavigationDrawerItem(
                    label = { Text("Favorites") },
                    selected = false,
                    onClick = { },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.Transparent,
                        selectedContainerColor = Color(0xFF333333)
                    )
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            containerColor = BlackScreen,
            topBar = {
                MainTopBar(
                    title = "CRYPTO TRACKER",
                    onMenuClick = {
                        scope.launch { drawerState.open() }
                    },
                    onSearchClick = {

                    }
                )
            },
            contentWindowInsets = WindowInsets(0.dp)
        ) {paddingValues ->
            PullToRefreshBox(
                isRefreshing = state.isRefreshing,
                onRefresh = {viewModel.refreshCoins() },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            )
            {

                LazyColumn(modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 40.dp)) {

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
}