package com.jddev.simpletouch.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.createGraph

enum class TransitionType {
    Default, SlideRightToLeft, SlideLeftToRight
}

private val defaultEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) =
    {
        fadeIn(animationSpec = tween(700))
    }
private val defaultExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) =
    {
        fadeOut(animationSpec = tween(700))
    }

@Composable
fun StUiNavHost(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier,
    route: String? = null,
    transitionType: TransitionType = TransitionType.SlideRightToLeft,
    tweenDuration: Int = 400,
    enterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        slideIntoContainer(
            towards = when (transitionType) {
                TransitionType.SlideRightToLeft -> AnimatedContentTransitionScope.SlideDirection.Left
                TransitionType.SlideLeftToRight -> AnimatedContentTransitionScope.SlideDirection.Right
                else -> AnimatedContentTransitionScope.SlideDirection.Start
            },
            animationSpec = tween(tweenDuration),
        )
    },
    exitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutOfContainer(
            towards = when (transitionType) {
                TransitionType.SlideRightToLeft -> AnimatedContentTransitionScope.SlideDirection.Left
                TransitionType.SlideLeftToRight -> AnimatedContentTransitionScope.SlideDirection.Right
                else -> AnimatedContentTransitionScope.SlideDirection.Start
            },
            animationSpec = tween(tweenDuration),
        )
    },
    popEnterTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
        slideIntoContainer(
            towards = when (transitionType) {
                TransitionType.SlideRightToLeft -> AnimatedContentTransitionScope.SlideDirection.Right
                TransitionType.SlideLeftToRight -> AnimatedContentTransitionScope.SlideDirection.Left
                else -> AnimatedContentTransitionScope.SlideDirection.Start
            },
            animationSpec = tween(tweenDuration),
        )
    },
    popExitTransition: (@JvmSuppressWildcards AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
        slideOutOfContainer(
            towards = when (transitionType) {
                TransitionType.SlideRightToLeft -> AnimatedContentTransitionScope.SlideDirection.Right
                TransitionType.SlideLeftToRight -> AnimatedContentTransitionScope.SlideDirection.Left
                else -> AnimatedContentTransitionScope.SlideDirection.Start
            },
            animationSpec = tween(tweenDuration),
        )
    },
    builder: NavGraphBuilder.() -> Unit,
) {
    NavHost(
        navController = navController,
        graph = remember(route, startDestination, builder) {
            navController.createGraph(startDestination, route, builder)
        },
        modifier = modifier,
        enterTransition = when(transitionType) {
            TransitionType.Default -> defaultEnterTransition
            else -> enterTransition
        },
        exitTransition = when(transitionType) {
            TransitionType.Default -> defaultExitTransition
            else -> exitTransition
        },
        popEnterTransition = when(transitionType) {
            TransitionType.Default -> enterTransition
            else -> popEnterTransition
        },
        popExitTransition = when(transitionType) {
            TransitionType.Default -> exitTransition
            else -> popExitTransition
        },
    )
}