package ru.owepkovr.freetoplaygames.data.model.network

import com.squareup.moshi.Json

data class Screenshot(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "image")
    val image: String?
)