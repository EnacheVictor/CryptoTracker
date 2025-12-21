package com.victorenache.cryptotracker.presentation.all_coins_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorenache.cryptotracker.core.Resource
import com.victorenache.cryptotracker.domain.use_cases.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AllCoinsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(AllCoinsState())
    val state: State<AllCoinsState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = AllCoinsState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = AllCoinsState(error = result.message ?: "Unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = AllCoinsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}