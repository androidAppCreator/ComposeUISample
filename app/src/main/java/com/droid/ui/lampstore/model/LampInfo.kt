package com.droid.ui.lampstore.model

import androidx.compose.ui.graphics.Color
import java.io.Serializable

data class LampInfo(
    val title : String,
    val description : String,
    val rating : Double,
    val price : Int,
    val image : Int,
    val mainImage : Int,
    val isRecommended : Boolean = false,
    val isPopular : Boolean = false,
    val lampType: LampType,
    val colorList : ArrayList<Color>
) : Serializable
