package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.taskcard.Status

@Composable
fun ButtonSection(
    state: Status,
    currentAssignee: Assignee,
    assignees: ImmutableList<Assignee>,
    onStateClick: (Status) -> Unit,
    onProfileClick: (Assignee) -> Unit,
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
            Status.entries.forEach { status ->
                StatusButton(
                    modifier = modifier.weight(1f),
                    currentStatus = state,
                    myStatus = status,
                    onClick = { onStateClick(status) }
                )
            }
        }
        Text(
            text = ComponentText.ASSIGNEE_LABEL_BUTTON_LABEL,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Gray20,
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            assignees.forEach { profile ->
                AssigneeLabelButton(
                    currentState = currentAssignee,
                    myState = profile,
                    onClick = { onProfileClick(profile) }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun ButtonSectionTodoStatusPreview() {
    val profiles = ProfilePreviewData().values.toImmutableList()
    ButtonSection(
        state = Status.TODO,
        currentAssignee = profiles[0],
        assignees = profiles,
        onStateClick = { },
        onProfileClick = {},
    )
}
