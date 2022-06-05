package ru.owepkovr.freetoplaygames.ui.gameslist

import ru.owepkovr.freetoplaygames.data.model.ui.GameUIModel

data class GamesListViewState(
    var isLoading: Boolean = false,
    var errorMessage: String? = null,
    var data: List<GameUIModel>? = null
)