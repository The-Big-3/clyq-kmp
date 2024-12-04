package org.big3.clyq.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

data class DialogContent(
    val title: String = "",
    val message: String = "",
    val confirmButton: String = "Confirm",
    val dismissButton: String? = "Cancel"
)


@Composable
fun ClyqDialog(
    showDialog: Boolean = true,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    dialogContent: DialogContent,
) {

    if (showDialog) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AlertDialog(
                onDismissRequest = {

                },
                title = {
                    Text(dialogContent.title)
                },
                text = {
                    Text(dialogContent.message)
                },
                confirmButton = {
                    TextButton(onClick = {
                        onConfirm()
                    }) {
                        Text(dialogContent.confirmButton)
                    }
                },
                dismissButton = {
                    if (dialogContent.dismissButton != null) {
                        TextButton(onClick = {
                            onDismiss()
                        }) {
                            Text(dialogContent.dismissButton)
                        }
                    }
                }
            )
        }

    }

}


fun getDialogContent(errorCode: Int): DialogContent {
    return when (errorCode) {
        401 -> DialogContent(
            title = "Force Logout",
            message = "Your login session has expired. Please log in again.",
            confirmButton = "Log out",
        )

        403 -> DialogContent(
            title = "Access Denied",
            message = "You do not have permission to access this resource.",
            confirmButton = "Go to Home",
            dismissButton = "Cancel"
        )

        500 -> DialogContent(
            title = "Server Error",
            message = "Something went wrong on our end. Please try again later.",
            confirmButton = "Retry",
            dismissButton = "Cancel"
        )

        else -> DialogContent(
            title = "Unknown Error",
            message = "An unexpected error occurred. Please try again.",
            confirmButton = "OK",
        )
    }
}
