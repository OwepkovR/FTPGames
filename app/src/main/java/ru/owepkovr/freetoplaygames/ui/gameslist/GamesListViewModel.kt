package ru.owepkovr.freetoplaygames.ui.gameslist

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.owepkovr.freetoplaygames.arch.ui.BaseViewModel
import ru.owepkovr.freetoplaygames.interactors.NetworkRepository

class GamesListViewModel(
    private val repository: NetworkRepository,
) : BaseViewModel<GamesListViewState>() {
    override var state = GamesListViewState()

    fun getGamesList() {
        state.apply {
            isLoading = true
            data = null
        }.render()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getGamesList()

                withContext(Dispatchers.Main) {
                    state.apply {
                        data = response
                        isLoading = false
                    }.render()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    state.apply {
                        errorMessage = e.message
                        isLoading = false
                    }.render()
                    Log.e("networkError", e.message.toString())
                }
            }
        }
    }
}