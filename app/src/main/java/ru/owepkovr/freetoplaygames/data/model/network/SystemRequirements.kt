package ru.owepkovr.freetoplaygames.data.model.network

import com.squareup.moshi.Json

data class SystemRequirements(
    @field:Json(name = "os")
    val os: String?,
    @field:Json(name = "processor")
    val processor: String?,
    @field:Json(name = "memory")
    val memory: String?,
    @field:Json(name = "graphics")
    val graphics: String?,
    @field:Json(name = "storage")
    val storage: String?,
)