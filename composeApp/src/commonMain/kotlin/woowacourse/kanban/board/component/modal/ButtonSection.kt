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
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.modal.ProfileState

@Composable
fun ButtonSection(
    state: TaskState,
    profileState: ProfileState,
    onStateClick: (TaskState) -> Unit,
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
            StateButton(currentState = state, myState = TaskState.TODO, onClick = { onStateClick(TaskState.TODO) })
            StateButton(currentState = state, myState = TaskState.PROGRESS, onClick = { onStateClick(TaskState.PROGRESS) })
            StateButton(currentState = state, myState = TaskState.DONE, onClick = { onStateClick(TaskState.DONE) })
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
            ProfileButton(currentState = profileState, myState = ProfileState.DINO, onClick = { onProfileClick(ProfileState.DINO) })
            ProfileButton(currentState = profileState, myState = ProfileState.PAMES, onClick = { onProfileClick(ProfileState.PAMES) })
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ButtonSectionPreview() {
    var state by remember { mutableStateOf(TaskState.TODO) }
    var profileState by remember { mutableStateOf(ProfileState.DINO) }
    ButtonSection(
        state = TaskState.TODO,
        profileState = profileState,
        onStateClick = { state = it },
        onProfileClick = { profileState = it },
    )
}
