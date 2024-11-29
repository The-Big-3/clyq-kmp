package org.big3.clyq.ui.views.groupView

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun GroupEditScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
    groupId: String,
//    viewModel: GroupScreenViewModel = hiltViewModel(),
    openMembersView: (memberType: String, participants: String) -> Unit,
    onEditEvent: (eventId: String) -> Unit,
    onAddEvent: () -> Unit,
    onBackPressed: () -> Unit,
) {

}