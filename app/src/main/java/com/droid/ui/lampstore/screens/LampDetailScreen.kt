package com.droid.ui.lampstore.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.droid.ui.lampstore.ui.theme.AlphaLightGrey
import com.droid.ui.lampstore.ui.theme.DarkCharcoalGrey
import com.droid.ui.lampstore.ui.theme.Orange
import com.droid.ui.lampstore.viewmodel.LampStoreViewModel

/**
 * This function creates the LampDetailScreen. Screen have the more detail information regarding the lamp.
 * @param navController nav controller for the main flow.
 * @param viewModel common LampStoreViewModel object.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LampDetailScreen(navController: NavController, viewModel: LampStoreViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkCharcoalGrey)

    ) {
        // The selectedLamp variable is used to store the selected lamp.
        val selectedLamp = viewModel.selectedLamp
        // The selectedColorTint variable is used to store the selected color tint for the lamp.
        var selectedColorTint by remember {
            mutableStateOf(selectedLamp?.colorList?.first())
        }
        selectedLamp?.let { lamp ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clip(RoundedCornerShape(25.dp))
                ) {
                    //To display top image
                    Image(
                        painter = painterResource(id = lamp.mainImage),
                        contentDescription = lamp.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop,
                        colorFilter = selectedColorTint?.let { ColorFilter.tint(it, blendMode = BlendMode.Darken) }
                    )
                    //To display top back and favourite icon on the top of image.
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 25.dp, end = 25.dp, top = 25.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.Top
                    ) {
                        FilledIconButton(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), onClick = { navController.popBackStack() },
                            colors = IconButtonDefaults.filledIconButtonColors(AlphaLightGrey)
                        ) {
                            Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Go Back", tint = Color.White, modifier = Modifier.clip(CircleShape))
                        }
                        FilledIconButton(
                            modifier = Modifier
                                .width(40.dp)
                                .height(40.dp), onClick = { /*TODO*/ },
                            colors = IconButtonDefaults.filledIconButtonColors(AlphaLightGrey)
                        ) {
                            Icon(Icons.Default.FavoriteBorder, contentDescription = "Favourite", tint = Color.White, modifier = Modifier.clip(CircleShape))
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .padding(top = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(text = lamp.title, fontSize = 18.sp, modifier = Modifier.weight(1f), color = Color.White, fontWeight = FontWeight.Bold)
                    Text(text = "${lamp.price} Rs", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }

                Text(
                    text = lamp.description, fontSize = 14.sp, fontWeight = FontWeight.ExtraLight, lineHeight = 20.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .padding(top = 16.dp),
                    color = Color.White
                )

                Text(
                    text = "Colors", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .padding(top = 25.dp),
                    color = Color.White
                )

                // The LazyRow() function is used to create a lazy row of color variation of the lamp.
                LazyRow(contentPadding = PaddingValues(25.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(lamp.colorList) { color ->
                        Card(
                            modifier = Modifier
                                .width(70.dp)
                                .height(70.dp),
                            shape = RoundedCornerShape(20.dp),
                            onClick = { selectedColorTint = color },
                            elevation = CardDefaults.cardElevation(5.dp)
                        ) {
                            Image(
                                modifier = Modifier.fillMaxSize(),
                                painter = painterResource(id = lamp.image),
                                colorFilter = ColorFilter.tint(color, blendMode = BlendMode.Darken),
                                contentDescription = lamp.title,
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }

            //To display bottom buttons of the screen which are fixed at the bottom
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier
                        .width(60.dp)
                        .height(60.dp),
                    border = BorderStroke(width = 0.1.dp, color = Color.White),
                    contentPadding = PaddingValues(15.dp),
                    shape = RoundedCornerShape(20.dp),
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        Icons.Outlined.ShoppingCart, contentDescription = "Cart", tint = Color.White, modifier = Modifier.fillMaxSize()
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Orange),
                    onClick = {

                    },
                    modifier = Modifier
                        .height(60.dp)
                        .weight(1f),
                    elevation = ButtonDefaults.elevatedButtonElevation(5.dp)
                ) {
                    Text("Buy Now", fontSize = 18.sp, fontWeight = FontWeight.Light)
                    Icon(
                        Icons.Default.KeyboardArrowRight,
                        contentDescription = "Buy Now",
                        tint = Color.White,
                        modifier = Modifier.padding(0.dp)
                    )
                }
            }
        }
    }
}