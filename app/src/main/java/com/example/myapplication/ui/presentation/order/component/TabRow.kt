package com.example.myapplication.ui.presentation.order.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Task
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapplication.ui.theme.primary
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabRow(
    navController: NavController
){
    val tabItems = listOf(
        TabItem(
            title = "Brownis",
        ),
        TabItem(
            title = "Pie Susu",
        ),
        TabItem(
            title = "Pie Jumbo",
        ),
        TabItem(
            title = "Risoles",
        ),
        TabItem(
            title = "Kue Lebaran",
        ),
        TabItem(
            title = "Mochi Bites",
        ),
        TabItem(
            title = "Dessert",
        )
    )
    Surface(
        modifier = Modifier
            .height(550.dp)
            .background(primary)
    ){
        var selectedTabIndex by remember{
            mutableIntStateOf(0)
        }
        val pagerState = rememberPagerState {
            tabItems.size
        }
        LaunchedEffect(selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }
        LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
            if(!pagerState.isScrollInProgress) {
                selectedTabIndex = pagerState.currentPage
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(primary)
        ){
            ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed{ index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = {
                            Text(text = item.title)
                        })
                }
            }
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ){ index ->
                Box (
                    modifier = Modifier.fillMaxSize(),
                ){
                    Content(index = index.absoluteValue)
                }
                
            }
        }
    }
}

data class TabItem(
    val title: String,
)