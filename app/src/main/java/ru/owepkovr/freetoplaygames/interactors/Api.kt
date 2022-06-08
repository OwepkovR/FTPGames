package ru.owepkovr.freetoplaygames.interactors

import retrofit2.Call
import retrofit2.http.*
import ru.owepkovr.freetoplaygames.data.model.network.Game
import ru.owepkovr.freetoplaygames.data.model.network.SingleGameInfo

interface Api {
    @GET("/api/game")
    fun getGame(@Query("id") id: String): Call<SingleGameInfo>

    @GET("/api/games")
    fun getGamesList(): Call<List<Game>>
}