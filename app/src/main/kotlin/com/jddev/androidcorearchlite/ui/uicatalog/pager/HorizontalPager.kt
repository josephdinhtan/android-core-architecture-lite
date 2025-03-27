package com.jddev.androidcorearchlite.ui.uicatalog.pager

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.jddev.simpletouch.ui.foundation.topappbar.StUiTopAppBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.min

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorizontalPagerDemo(onBack: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            StUiTopAppBar(
                onBack = onBack,
                title = "Bottom Navigation",
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item { Text(text = "Fast scroll able") }
            item { NormalPager(coroutineScope) }
            item { Spacer(Modifier.height(16.dp)) }
            item { Text(text = "Scroll one by one") }
            item { MusicPager(coroutineScope) }
            item { Spacer(Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun NormalPager(coroutineScope: CoroutineScope) {
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    val fling = PagerDefaults.flingBehavior(
        state = pagerState, pagerSnapDistance = PagerSnapDistance.atMost(10)
    )
    HorizontalPager(
        state = pagerState, flingBehavior = fling
    ) { page ->
        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(200.dp)
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }, colors = CardDefaults.cardColors(
                containerColor = if (page % 2 == 0) Color.Blue else Color.Yellow,
            )
        ) {
            Text(text = "page $page")
        }
    }
    Spacer(Modifier.height(16.dp))
    Text(text = "Page: ${pagerState.currentPage}")
    Spacer(Modifier.height(16.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(max(pagerState.currentPage - 1, 0))
            }
        }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
        IconButton(onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    min(
                        pagerState.currentPage + 1, pagerState.pageCount - 1
                    )
                )
            }
        }) { Icon(Icons.AutoMirrored.Filled.ArrowForward, null) }
    }
}

@Composable
private fun MusicPager(coroutineScope: CoroutineScope) {
    val pagerState = rememberPagerState(pageCount = {
        10
    })
    HorizontalPager(
        state = pagerState
    ) { page ->
        Card(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(200.dp)
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f, stop = 1f, fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }, colors = CardDefaults.cardColors(
                containerColor = if (page % 2 == 0) Color.Blue else Color.Yellow,
            )
        ) {
            Text(text = "page $page")
        }
    }
    Spacer(Modifier.height(20.dp))
    Text(text = "Page: ${pagerState.currentPage}")
    Spacer(Modifier.height(20.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(max(pagerState.currentPage - 1, 0))
            }
        }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) }
        IconButton(onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(
                    min(
                        pagerState.currentPage + 1, pagerState.pageCount - 1
                    )
                )
            }
        }) { Icon(Icons.AutoMirrored.Filled.ArrowForward, null) }
    }
}