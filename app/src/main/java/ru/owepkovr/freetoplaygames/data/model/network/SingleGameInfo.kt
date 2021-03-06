package ru.owepkovr.freetoplaygames.data.model.network

import com.squareup.moshi.Json

data class SingleGameInfo(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "thumbnail")
    val thumbnail: String?,
    @field:Json(name = "status")
    val status: String?,
    @field:Json(name = "short_description")
    val shortDescription: String?,
    @field:Json(name = "description")
    val description: String?,
    @field:Json(name = "game_url")
    val gameUrl: String?,
    @field:Json(name = "genre")
    val genre: String?,
    @field:Json(name = "platform")
    val platform: String?,
    @field:Json(name = "publisher")
    val publisher: String?,
    @field:Json(name = "developer")
    val developer: String?,
    @field:Json(name = "release_date")
    val releaseDate: String?,
    @field:Json(name = "freetogame_profile_url")
    val freetogameProfileUrl: String?,
    @field:Json(name = "minimum_system_requirements")
    val minimumSystemRequirements: SystemRequirements?,
    @field:Json(name = "screenshots")
    val screenshots: List<Screenshot?>?
)