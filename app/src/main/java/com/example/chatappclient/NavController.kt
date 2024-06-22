package com.example.chatappclient

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chatappclient.presentation.auth.forgotPassword.ForgotPasswordScreen
import com.example.chatappclient.presentation.auth.login.LoginScreen
import com.example.chatappclient.presentation.auth.signup.SignUpScreen
import com.example.chatappclient.presentation.common.Routes
import com.example.chatappclient.presentation.splash.SplashScreen

@Composable
fun MainScreenContainer() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {
        composable(Routes.Splash.route) {
            SplashScreen(navController)
        }
        composable(Routes.Login.route) {
            LoginScreen(navController)
        }
        composable(Routes.SignUp.route) {
            SignUpScreen(navController)
        }
        composable(Routes.ForgotPassword.route) {
            ForgotPasswordScreen(navController)
        }
        composable(Routes.FriendsList.route) {
//            FriendsListScreen(navController)
        }
        composable(route = "${Routes.ChatRoom.route}${Routes.ChatRoom.ARGS}", arguments = listOf(
            navArgument(Routes.ChatRoom.ARG_FRIEND_NAME) { type = NavType.StringType },
            navArgument(Routes.ChatRoom.ARG_FRIEND_EMAIL) { type = NavType.StringType },
            navArgument(Routes.ChatRoom.ARG_FRIEND_AVATAR) { type = NavType.StringType }
        )) {
//            ChatRoomScreen(
//                navController,
//                friendName = it.arguments?.getString(Routes.ChatRoom.ARG_FRIEND_NAME).orEmpty(),
//                friendEmail = it.arguments?.getString(Routes.ChatRoom.ARG_FRIEND_EMAIL).orEmpty(),
//                friendAvatar = it.arguments?.getString(Routes.ChatRoom.ARG_FRIEND_AVATAR).orEmpty()
//            )
        }
    }
}