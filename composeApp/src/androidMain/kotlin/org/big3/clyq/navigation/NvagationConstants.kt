package org.big3.clyq.navigation


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.ui.graphics.vector.ImageVector


sealed class MainDestinations (val route: String){
    data object Auth:MainDestinations("auth")
    data object Main:MainDestinations("main")
    data object Splash:MainDestinations("splash")
    data object GroupDetail:MainDestinations("groupDetails")
    data object NewGroup:MainDestinations("createGroup")
    data object MemberDetails:MainDestinations("memberDetails/{displayMemberType}/{participants}"){
        fun createRoute(displayMemberType:String,participants:String) = "memberDetails/$displayMemberType/$participants"
    }
}

sealed class HomeSection(val route: String){
    data object HomeMain:HomeSection("home_main")
    data object EventDetails:HomeSection("event_details/{eventId}/{userInfo}"){
        fun  createRoute(eventId:String,userInfo:String) = "event_details/$eventId/$userInfo"
    }
}

sealed class GroupSection(val route: String){
    data object GroupMain:GroupSection("group_main")
    data object EditGroup:GroupSection("group_edit/{groupId}"){
        fun createRoute(groupId:String) = "group_edit/$groupId"
    }
    data object EditEvent:GroupSection("event_edit/{eventId}"){
        fun createRoute(eventId: String) = "event_edit/$eventId"
    }
    data object CreateEvent:GroupSection("event_create/{groupId}"){
        fun createRoute(groupId: String) = "event_create/$groupId"
    }
}


enum class BottomNavItem(val route: String, val icon: ImageVector, val title:String){
    Home("home", Icons.Filled.Home, "Home"),
    Groups("groups", Icons.Filled.Share,"Groups"),
    Search("search", Icons.Filled.Search,"Find"),
    Profile("profile", Icons.Filled.AccountCircle, "Profile"),
}