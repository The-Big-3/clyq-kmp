package org.big3.clyq.ui.views.groupView

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun MyGroupScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
//    groupViewModel: GroupScreenViewModel = hiltViewModel(),
    onNavigateToRoute: (String) -> Unit,
    openGroupDetail: (groupId: String) -> Unit,
    openGroupAsOwner: (groupId: String) -> Unit,
    openCreateGroup: () -> Unit,
) {

}