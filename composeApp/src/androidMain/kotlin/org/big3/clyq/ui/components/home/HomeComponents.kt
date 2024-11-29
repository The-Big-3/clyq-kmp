package org.big3.clyq.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.big3.clyq.Utils
import org.big3.clyq.core.entity.UserServiceItem
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ReminderSection(
    userInfo: UserServiceItem,
    modifier: Modifier = Modifier,
    openGroupList: () -> Unit,
    openEventList: () -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xff7057f5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(68.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {

            AsyncImage(
                model = userInfo.user.imageUrl,
                contentDescription = "profile image",
                modifier = Modifier.clip(CircleShape).size(90.dp)
            )

            Spacer(Modifier.size(20.dp))
            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center) {
                Spacer(Modifier.size(10.dp))
                Text(
                    text = Utils.getTimeOfDay(),
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.size(5.dp))
                Text(
                    text = LocalDate.now().format(
                        DateTimeFormatter.ofPattern("EE dd MMM", Locale.ENGLISH)
                    ),
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }

        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(90.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .fillMaxHeight()
                        .width(64.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = userInfo.numberOfJoinedGroups.toString(),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Groups",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(64.dp)
                        .width(1.dp)
                        .background(Color.White)
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxHeight()
                        .width(64.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = userInfo.numberOfJoinedEvents.toString(),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        text = "Events",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

        }

    }
}
