package ru.owepkovr.freetoplaygames.arch.navigation

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavController

object NavigationRouter {

    /**
     * Executes navigation [command] for given navigation [controller]
     */
    fun navigate(controller: NavController, command: NavigationCommand, bundle: Bundle?) {
        when (command) {
            is NavigationCommand.ById -> controller.navigate(command.id, bundle)
            is NavigationCommand.ByAction -> controller.navigate(command.action, command.navOptions)
            is NavigationCommand.ByDeepLink -> {
                val uri = Uri.parse(command.deepLink)
                controller.navigate(uri, command.navOptions)
            }
            NavigationCommand.Back -> {
                controller.previousBackStackEntry?.savedStateHandle?.set("params", bundle)
                controller.navigateUp()
            }
        }
    }

}