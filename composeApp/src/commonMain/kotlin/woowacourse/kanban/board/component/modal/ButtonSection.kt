package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.model.taskcard.TaskStatus
import woowacourse.kanban.board.model.taskcard.ProfileState

@Composable
fun ButtonSection(
    state: TaskStatus,
    profileState: ProfileState,
    onStateClick: (TaskStatus) -> Unit,
    onProfileClick: (ProfileState) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(
            text = ComponentText.STATE_BUTTON_LABEL,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray20,
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            StateButton(currentState = state, myState = TaskStatus.TODO, onClick = { onStateClick(TaskStatus.TODO) })
            StateButton(currentState = state, myState = TaskStatus.PROGRESS, onClick = { onStateClick(TaskStatus.PROGRESS) })
            StateButton(currentState = state, myState = TaskStatus.DONE, onClick = { onStateClick(TaskStatus.DONE) })
        }
        Text(
            text = ComponentText.PROFILE_BUTTON_LABEL,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray20,
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            ProfileButton(
                currentState = profileState,
                myState = ProfileState("디노",Res.drawable.profile),
                onClick = { onProfileClick(ProfileState("Dino",Res.drawable.profile)) }
            )
            ProfileButton(
                currentState = profileState,
                myState = ProfileState("페임스",Res.drawable.profile),
                onClick = { onProfileClick(ProfileState("페임스",Res.drawable.profile)) }
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ButtonSectionPreview() {
    var state by remember { mutableStateOf(TaskStatus.TODO) }
    var profileState by remember { mutableStateOf(ProfileState("다이노",Res.drawable.profile)) }
    ButtonSection(
        state = TaskStatus.TODO,
        profileState = profileState,
        onStateClick = { state = it },
        onProfileClick = { profileState = it },
    )
}
