package ru.owepkovr.freetoplaygames.interactors

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.owepkovr.freetoplaygames.data.model.network.Game
import ru.owepkovr.freetoplaygames.data.model.network.SingleGameInfo
import ru.owepkovr.freetoplaygames.data.model.ui.GameUIModel
import ru.owepkovr.freetoplaygames.interactors.Api

class NetworkRepository(
    private val api: Api
) {
    @WorkerThread
    suspend fun getGamesList() : List<GameUIModel>? = withContext(Dispatchers.IO) {
        try {
            val response = api.getGamesList().execute().body()
            val result = mutableListOf<GameUIModel>()

            if (response?.size ?: 0  == 0) return@withContext null

            response?.map {
                result.add(GameUIModel(
                    id = it.id,
                    title = it.title,
                    thumbnail = it.thumbnail,
                    shortDescription = it.shortDescription,
                    gameUrl = it.gameUrl,
                    genre = it.genre,
                    platform = it.platform,
                    publisher = it.publisher,
                    developer = it.developer,
                    releaseDate = it.releaseDate,
                    freetogameProfileUrl = it.freetogameProfileUrl
                ))
            }

            return@withContext result
        } catch (e: Exception) {
            Log.e("getGamesList Error", "${e.message}")
        }

        return@withContext null
    }

    @WorkerThread
    suspend fun getGameInfo(id: String): SingleGameInfo? = withContext(Dispatchers.IO) {
        try {
            val response = api.getGame(id).execute()

            return@withContext response.body()
        } catch (e: Exception) {
            print(e)
        }

        return@withContext null
    }
}