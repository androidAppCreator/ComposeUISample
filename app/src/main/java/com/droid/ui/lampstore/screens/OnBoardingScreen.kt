@file:OptIn(ExperimentalPagerApi::class)

package com.droid.ui.lampstore.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.droid.ui.lampstore.model.OnBoardingData
import com.droid.ui.lampstore.ui.theme.LightGrey
import com.droid.ui.lampstore.ui.theme.Orange
import com.droid.ui.lampstore.viewmodel.LampStoreViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * This function creates the OnBoardingScreen.
 * @param navController parent navController to navigate between the screen.
 * @param viewModel common LampStoreViewModel object.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    viewModel: LampStoreViewModel
) {
    // The pagerState variable is used to track the current page in the ViewPager.
    val pagerState = rememberPagerState(viewModel.getOnBoardingScreenList().size)
    // The scope variable is used to launch coroutines.
    val scope = rememberCoroutineScope()

    OnBoardingPager(
        item = viewModel.getOnBoardingScreenList(),
        pagerState = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        scope = scope,
        navController = navController,
        viewModel =  viewModel,
        context = LocalContext.current
    )
}

/**
 * The OnBoardingPager() function is used to render the ViewPager.
 * This has the actual content of the viewPager, the image and the content is scrolled, the dot indicator and buttons remains on the screen
 * @param item list of onBoardingData that contains viewpager info.
 * @param pagerState to track the current page in the ViewPager
 * @param scope to launch coroutines
 * @param navController parent navController to navigate between the screen.
 * @param viewModel common LampStoreViewModel object.
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    scope: CoroutineScope,
    navController: NavController,
    viewModel: LampStoreViewModel,
    context : Context
) {
    // The Box() function is used to create a container that fills the entire screen.
    Box(modifier = modifier.fillMaxSize()) {
        // The HorizontalPager() function is used to create a ViewPager that scrolls horizontally.
        HorizontalPager(state = pagerState) { page ->
            OnBoardingPage(item[page])
        }
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DotsIndicator(
                totalDots = item.size,
                selectedIndex = pagerState.currentPage,
                Color.White,
                LightGrey,
            )
            BottomButtons(pagerState, scope){
                viewModel.setOnboardingFlowVisited(context)
                navController.navigate("dashboard")
            }
        }
    }
}

/**
 * The OnBoardingPage() contains the background image, the text, sub text and descriptions.
 * @param page contains the onBoardingScreen detail information to be displayed.
 */
@Composable
private fun OnBoardingPage(page: OnBoardingData) {
    //Display the background image
    Image(painter = painterResource(id = page.image), contentDescription = page.title, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)

    //Display the text, sub text and descriptions.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp, end = 70.dp)
            .padding(vertical = 25.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = page.title,
            color = Color.White,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp
        )
        Text(
            text = page.subTitle,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
        )
        Text(
            text = page.description,
            color = Color.White,
            modifier = Modifier.padding(top = 18.dp),
            fontWeight = FontWeight.ExtraLight,
            fontSize = 14.sp,
        )
    }
}

/**
 * The DotsIndicator() function is used to create a series of dots that indicate the current page of the ViewPager.
 * @param totalDots total number of dots.
 * @param selectedIndex current selected dot.
 * @param selectedColor color for the selected dot.
 * @param unSelectedColor color for the unselected dot.
 */
@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {
    // The LazyRow() function is used to create a horizontal list of items.
    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(top = 100.dp)

    ) {
        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}

/**
 * The BottomButtons() function is used to create two buttons at the bottom of the screen.
 * @param pagerState to track the current page in the ViewPager.
 * @param scope to launch coroutines
 * @param onOnBoardingVisited on click of "Skip" or last item "Next" mark the onBoarding Screen visited as true.
 */
@Composable
private fun BottomButtons(pagerState: PagerState, scope: CoroutineScope, onOnBoardingVisited: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 35.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // This first button is used to skip the onboarding flow.
        TextButton(
            onClick = { onOnBoardingVisited()},
            modifier = Modifier.padding(horizontal = 8.dp),
        ) {
            Text("Skip", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Light)
        }

        // This second button is used to continue the onboarding flow.
        ElevatedButton(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Orange),
            onClick = {
                if (pagerState.currentPage < (pagerState.pageCount.minus(1))) {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                } else {
                    onOnBoardingVisited()
                }
            },
            modifier = Modifier.padding(horizontal = 8.dp),
            contentPadding = PaddingValues(horizontal = 35.dp, vertical = 12.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(5.dp)
        ) {
            Text("Next", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Light)
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "drawable icons",
                tint = Color.White,
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

