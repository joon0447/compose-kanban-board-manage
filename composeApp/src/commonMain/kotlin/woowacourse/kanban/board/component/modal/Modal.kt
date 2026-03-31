package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.component.workspace.ModalState
import woowacourse.kanban.board.component.workspace.rememberModalState
import woowacourse.kanban.board.model.modal.ModalType
import woowacourse.kanban.board.model.modal.TextInputState
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.TaskDescription
import woowacourse.kanban.board.model.taskcard.TaskTag
import woowacourse.kanban.board.model.taskcard.TaskTags
import woowacourse.kanban.board.model.taskcard.TaskTitle

@Composable
fun Modal(
    assignees: ImmutableList<Assignee>,
    onClickClose: () -> Unit,
    onClickTaskCreate: (TaskCardData) -> Unit,
    modalType: ModalType,
    modalState: ModalState,
    modifier: Modifier = Modifier,
    data: TaskCardData? = null,
) {
    LaunchedEffect(data) {
        if (data != null) modalState.loadData(data)
        else modalState.clear()
    }
    val titleInputState = TextInputState(
        value = modalState.title,
        onChange = { modalState.title = it },
        isError = modalState.isTaskTitleValid.not(),
    )
    val descriptionInputState = TextInputState(
        value = modalState.description,
        onChange = { modalState.description = it },
    )
    val tagsInputState = TextInputState(
        value = modalState.tags,
        onChange = { modalState.tags = it },
        isError = modalState.isTaskTagsValid.not(),
    )

    val headerLabel = when (modalType) {
        is ModalType.Create -> ComponentText.CREATE_MODAL_HEADER_LABEL
        is ModalType.Edit -> ComponentText.EDIT_MODAL_HEADER_LABEL
    }

    Card(
        modifier = modifier
            .width(800.dp)
            .padding(50.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            Header(
                label = headerLabel,
                onClickClose = onClickClose,
            )
            HorizontalDivider()
            TextInputSection(
                titleInputState = titleInputState,
                descriptionInputState = descriptionInputState,
                tagsInputState = tagsInputState,
            )
            ButtonSection(
                state = modalState.status,
                currentAssignee = modalState.assignees,
                assignees = assignees,
                onStateClick = { modalState.status = it },
                onProfileClick = { modalState.assignees = it },
            )
            Footer(
                onClickClose = onClickClose,
                onClickTaskCreate = {
                    val data = TaskCardData(
                        taskTitle = TaskTitle(value = modalState.title),
                        taskDescription = TaskDescription(value = modalState.description),
                        taskTags = TaskTags(TaskTag.extractedTags(modalState.tags).toImmutableList()),
                        status = modalState.status,
                        assignee = modalState.assignees,
                    )
                    onClickTaskCreate(data)
                },
                isButtonEnabled = modalState.isTaskTitleValid && modalState.isTaskTagsValid,
                modalType = modalType,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000, heightDp = 1000)
@Composable
private fun CreateModalPreview() {
    val profiles = ProfilePreviewData().values.toImmutableList()
    Modal(
        assignees = profiles,
        modalType = ModalType.Create({}),
        onClickClose = {},
        onClickTaskCreate = {},
        modalState = rememberModalState(profiles)
    )
}

@Preview(showBackground = true, widthDp = 1000, heightDp = 1000)
@Composable
private fun EditModalPreview() {
    val profiles = ProfilePreviewData().values.toImmutableList()
    Modal(
        assignees = profiles,
        modalType = ModalType.Edit(
            onDelete = {},
            onUpdate = {},
        ),
        onClickClose = {},
        onClickTaskCreate = {},
        modalState = rememberModalState(profiles)
    )
}
