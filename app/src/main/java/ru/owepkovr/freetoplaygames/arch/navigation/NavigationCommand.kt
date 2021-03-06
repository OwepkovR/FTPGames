package ru.owepkovr.freetoplaygames.arch.navigation

import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

sealed class NavigationCommand {
    data class ById(val id: Int) : NavigationCommand()
    data class ByAction(val action: NavDirections, val navOptions: NavOptions? = null) : NavigationCommand()
    data class ByDeepLink(val deepLink: String, val navOptions: NavOptions? = null) : NavigationCommand()
    object Back : NavigationCommand()
}