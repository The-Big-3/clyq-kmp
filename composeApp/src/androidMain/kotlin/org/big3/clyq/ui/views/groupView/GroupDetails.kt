package org.big3.clyq.ui.views.groupView

import androidx.compose.runtime.Composable

@Composable
fun GroupDetailScreen(
    groupId: String,
//    viewModel: GroupScreenViewModel = hiltViewModel(),
//    eventViewModel: EventViewModel = hiltViewModel(),
    openEditMode: (groupId: String) -> Unit,
    openMembersView: (memberType: String, participants: String) -> Unit,
    goBackPress: () -> Unit,
){

}