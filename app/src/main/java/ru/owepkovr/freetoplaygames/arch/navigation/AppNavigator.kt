package ru.owepkovr.freetoplaygames.arch.navigation

import freetoplaygames.R

object AppNavigator {
    fun back(): NavigationCommand {
        return NavigationCommand.Back
    }

    fun toGamesList(): NavigationCommand {
        return NavigationCommand.ById(R.id.action_gameslist)
    }
}