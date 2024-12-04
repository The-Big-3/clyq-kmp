package org.big3.clyq.ui.views.eventView

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun EventEditScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
//    viewModel: EventViewModel = hiltViewModel(),
    eventId: String,
    openMembersView: (memberType: String, participants: String) -> Unit,
    onBackPressed: () -> Unit,
){

}


@Composable
fun EventCreateScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    groupId: String,
//    viewModel: EventViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
){

}