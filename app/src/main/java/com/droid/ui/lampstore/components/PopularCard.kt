package com.droid.ui.lampstore.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droid.ui.lampstore.model.LampInfo
import com.droid.ui.lampstore.ui.theme.CharcoalGrey
import com.droid.ui.lampstore.ui.theme.Orange
import com.droid.ui.lampstore.ui.theme.Yellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularCard(lamps: List<LampInfo>, onLampSelected: (lampInfo: LampInfo) -> Unit) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 25.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(lamps) { lamp ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = CharcoalGrey,
                ),
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier
                    .height(110.dp)
                    .width(250.dp),
                onClick = {
                    onLampSelected(lamp)
                }
            ) {
                Row {
                    Image(
                        modifier = Modifier
                            .wrapContentHeight()
                            .width(120.dp)
                            .fillMaxHeight(), contentScale = ContentScale.Crop,
                        painter = painterResource(id = lamp.image), contentDescription = lamp.title
                    )
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text(text = lamp.title, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Spacer(modifier = Modifier.padding(top = 5.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Default.Star, tint = Yellow, contentDescription = "Rating", modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                            Text(
                                text = lamp.rating.toString(),
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.ExtraLight
                            )
                        }
                        Spacer(modifier = Modifier.padding(vertical = 5.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${lamp.price} Rs",
                                color = Orange,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            FilledIconButton(
                                modifier = Modifier
                                    .width(25.dp)
                                    .height(25.dp), onClick = { /*TODO*/ },
                                colors = IconButtonDefaults.filledIconButtonColors(Orange),
                            ) {
                                Icon(Icons.Filled.Add, contentDescription = "Add", tint = Color.White, modifier = Modifier.clip(CircleShape).padding(4.dp))
                            }
                        }
                    }
                }
            }
        }
    }
}