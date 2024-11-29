package org.big3.clyq.ui.views.eventView

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun EventDetailsScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
//    eventViewModel: EventViewModel = hiltViewModel(),
    eventId: String,
    userEmail: String,
    openMembersView: (memberType: String, participants:String) -> Unit,
    onBackPressed: () -> Unit,
){

}