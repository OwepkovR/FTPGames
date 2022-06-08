package ru.owepkovr.freetoplaygames.data.model.ui

import ru.owepkovr.freetoplaygames.arch.recycler.RecyclerViewItem
import ru.owepkovr.freetoplaygames.data.model.network.Screenshot
import ru.owepkovr.freetoplaygames.data.model.network.SystemRequirements

data class GameUIModel(
    val id: String,
    val title: String,
    val thumbnail: String,
    val shortDescription: String?,
    val gameUrl: String?,
    val genre: String?,
    val platform: String?,
    val publisher: String?,
    val developer: String?,
    val releaseDate: String?,
    val freetogameProfileUrl: String?,
) : RecyclerViewItem {
    override fun getId(): Any {
        return id
    }
}