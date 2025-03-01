package com.jddev.simpletouch.ui.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.State
import androidx.compose.runtime.collection.MutableVector
import androidx.compose.runtime.collection.mutableVectorOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

private fun Color.Companion.orange() = Color(0xFFF18E00)
private fun Color.Companion.gray() = Color(0xFFC2C2C2)

@Composable
fun StUiBottomNavigation(
    modifier: Modifier,
    navigationItems: StNavigationScope.() -> Unit,
    containerColor: Color = MaterialTheme.colorScheme.background,
    contentColor: Color = MaterialTheme.colorScheme.onBackground,
    content: @Composable () -> Unit = {}
) {
    val scope by rememberStStateOfItems(navigationItems)
    Surface(modifier = modifier, color = containerColor, contentColor = contentColor) {
        Column(Modifier.fillMaxSize()) {
            Column(Modifier.weight(1f)) { content() }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .height(54.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                scope.itemList.forEach {
                    StNavigationBarItem(
                        modifier = it.modifier,
                        selected = it.selected,
                        onClick = it.onClick,
                        icon = it.icon,
                        enabled = it.enabled,
                        label = it.label,
                        alwaysShowLabel = it.alwaysShowLabel,
                        interactionSource = it.interactionSource
                    )
                }
            }
        }
    }
}

@Composable
private fun RowScope.StNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true,
    colors: NavigationBarItemColors = NavigationBarItemDefaults.colors()
        .copy(
            selectedIconColor = Color.orange(),
            selectedTextColor = Color.orange(),
            unselectedIconColor = Color.gray(),
            unselectedTextColor = Color.gray(),
        ),
    interactionSource: MutableInteractionSource? = null
) {
    var itemWidth by remember { mutableIntStateOf(0) }
    Box(
        modifier
            .selectable(
                selected = selected,
                onClick = onClick,
                enabled = enabled,
                role = Role.Tab,
                interactionSource = interactionSource,
                indication = null,
            )
            .weight(1f)
            .onSizeChanged { itemWidth = it.width },
        contentAlignment = Alignment.Center,
        propagateMinConstraints = true,
    ) {
        val iconColor by animateColorAsState(
            targetValue = colors.iconColor(selected = selected, enabled = enabled),
            animationSpec = tween(100)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(LocalContentColor provides iconColor, content = icon)
            label?.let {
                CompositionLocalProvider(
                    LocalContentColor provides iconColor,
                    content = it
                )
            }
        }
    }
}

sealed interface StNavigationScope {
    fun item(
        selected: Boolean,
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        label: @Composable (() -> Unit)? = null,
        alwaysShowLabel: Boolean = true,
        badge: (@Composable () -> Unit)? = null,
        colors: NavigationBarItemColors? = null,
        interactionSource: MutableInteractionSource? = null
    )
}

@Composable
private fun rememberStStateOfItems(
    content: StNavigationScope.() -> Unit
): State<StNavigationItemProvider> {
    val latestContent = rememberUpdatedState(content)
    return remember { derivedStateOf { NavigationSuiteScopeImpl().apply(latestContent.value) } }
}

private class NavigationSuiteScopeImpl : StNavigationScope, StNavigationItemProvider {

    override fun item(
        selected: Boolean,
        onClick: () -> Unit,
        icon: @Composable () -> Unit,
        modifier: Modifier,
        enabled: Boolean,
        label: @Composable (() -> Unit)?,
        alwaysShowLabel: Boolean,
        badge: (@Composable () -> Unit)?,
        colors: NavigationBarItemColors?,
        interactionSource: MutableInteractionSource?
    ) {
        itemList.add(
            StNavigationItem(
                selected = selected,
                onClick = onClick,
                icon = icon,
                modifier = modifier,
                enabled = enabled,
                label = label,
                alwaysShowLabel = alwaysShowLabel,
                badge = badge,
                colors = colors,
                interactionSource = interactionSource
            )
        )
    }

    override val itemList: MutableVector<StNavigationItem> = mutableVectorOf()

    override val itemsCount: Int
        get() = itemList.size
}

private interface StNavigationItemProvider {
    val itemsCount: Int
    val itemList: MutableVector<StNavigationItem>
}

private fun NavigationBarItemColors.iconColor(selected: Boolean, enabled: Boolean): Color = when {
    !enabled -> disabledIconColor
    selected -> selectedIconColor
    else -> unselectedIconColor
}


private class StNavigationItem(
    val selected: Boolean,
    val onClick: () -> Unit,
    val icon: @Composable () -> Unit,
    val modifier: Modifier,
    val enabled: Boolean,
    val label: @Composable (() -> Unit)?,
    val alwaysShowLabel: Boolean,
    val badge: (@Composable () -> Unit)?,
    val colors: NavigationBarItemColors?,
    val interactionSource: MutableInteractionSource?
)