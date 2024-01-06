package com.droid.ui.lampstore.viewmodel

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.droid.ui.lampstore.R
import com.droid.ui.lampstore.model.BottomNavItem
import com.droid.ui.lampstore.model.LampInfo
import com.droid.ui.lampstore.model.LampType
import com.droid.ui.lampstore.model.OnBoardingData
import com.droid.ui.lampstore.ui.theme.SampleColor1
import com.droid.ui.lampstore.ui.theme.SampleColor2
import com.droid.ui.lampstore.ui.theme.SampleColor3

class LampStoreViewModel : ViewModel() {

    var selectedLamp by mutableStateOf<LampInfo?>(null)
        private set

    fun getOnBoardingScreenList(): ArrayList<OnBoardingData> {
        return ArrayList<OnBoardingData>().apply {
            add(OnBoardingData(R.drawable.lamp_onboarding, "Lamp", "Lightning House", "Decorate your light to make it look deluxe"))
            add(OnBoardingData(R.drawable.lamp_onboarding, "Lamp", "Lightning House", "Bright ideas, beautiful lamps."))
            add(OnBoardingData(R.drawable.lamp_onboarding, "Lamp", "Lightning House", "Bright ideas, beautiful lamps."))
        }
    }

    fun getChipList(): ArrayList<String> {
        return ArrayList<String>().apply {
            add("All")
            add("Buffet")
            add("Torchiere")
            add("Unique")
        }
    }


    fun getLampLists(): ArrayList<LampInfo> {
        return ArrayList<LampInfo>().apply {
            add(LampInfo("Velly Lamp", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.8, 100, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = true, isPopular = false, lampType = LampType.BUFFET, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Giryi Lamp", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.9, 200, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = false, isPopular = true, LampType.BUFFET, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Beauty Lamp", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0, 300, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = true, isPopular = true, LampType.BUFFET, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Velly Lamp 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.7, 150, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = true, isPopular = false, lampType = LampType.TORCHIERE, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Giryi Lamp 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.2, 250, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = false, isPopular = true, LampType.TORCHIERE, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Beauty Lamp 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.1, 350, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = true, isPopular = true, LampType.TORCHIERE, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Velly Lamp 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 4.0, 400, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = true, isPopular = false, lampType = LampType.UNIQUE, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Giryi Lamp 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 3.9, 500, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = false, isPopular = true, LampType.UNIQUE, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
            add(LampInfo("Beauty Lamp 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt", 5.0, 600, R.drawable.sample_lamp, R.drawable.lamp_detail_image, isRecommended = true, isPopular = true, LampType.UNIQUE, arrayListOf(SampleColor1, SampleColor2, SampleColor3)))
        }
    }

    fun getBottomNavigationItem(): ArrayList<BottomNavItem> {
        return ArrayList<BottomNavItem>().apply {
            add(BottomNavItem("home", Icons.Filled.Home, Icons.Outlined.Home, "Home"))
            add(BottomNavItem("search", Icons.Filled.Search, Icons.Outlined.Search, "Search"))
            add(BottomNavItem("cart", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart, "Cart"))
            add(BottomNavItem("notification", Icons.Filled.Notifications, Icons.Outlined.Notifications, "Notification"))
            add(BottomNavItem("profile", Icons.Filled.Person, Icons.Outlined.Person, "Profile"))
        }
    }

    fun addSelectedLamp(lampInfo: LampInfo) {
        selectedLamp = lampInfo
    }

    fun setOnboardingFlowVisited(context: Context) {
        val sharedPreference = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        sharedPreference.edit().apply {
            putBoolean("isOnboardingFlowCompleted", true)
        }.apply()
    }

    fun isOnBoardingFlowVisited(context: Context) : Boolean{
        val sharedPreference =  context.getSharedPreferences(PREFERENCE_NAME,Context.MODE_PRIVATE)
        return sharedPreference.getBoolean("isOnboardingFlowCompleted", false)
    }

    companion object{
        const val PREFERENCE_NAME = "lamp_preference"
    }

}