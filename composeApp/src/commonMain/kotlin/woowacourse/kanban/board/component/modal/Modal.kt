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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.model.state.ModalState
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.modal.TextInputState
import woowacourse.kanban.board.model.taskcard.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun Modal(
    modalState: ModalState,
    onClickClose: () -> Unit,
    onClickTaskCreate: (TaskCardData) -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalState = remember { ModalState() }
    val titleInputState = TextInputState(
        value = modalState.title,
        onChange = { modalState.title = it },
        isError = modalState.isTitleValid.not(),
    )
    val descriptionInputState = TextInputState(
        value = modalState.description,
        onChange = { modalState.description = it },
    )
    val tagsInputState = TextInputState(
        value = modalState.tags,
        onChange = { modalState.tags = it },
        isError = modalState.isTagsValid.not(),
    )

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
                onClickClose = onClickClose
            )
            HorizontalDivider()
            TextInputSection(
                titleInputState = titleInputState,
                descriptionInputState = descriptionInputState,
                tagsInputState = tagsInputState,
            )
            ButtonSection(
                state = modalState.status,
                profileState = modalState.profileState,
                onStateClick = { modalState.status = it },
                onProfileClick = { modalState.profileState = it },
            )
            Footer(
                onClickClose = onClickClose,
                onClickTaskCreate = {
                    val data = TaskCardData(
                        title = Title(value = modalState.title),
                        description = Description(value = modalState.description),
                        tags = Tags(Tag.extractedTags(modalState.tags)),
                        status = modalState.status,
                        profile = modalState.profileState,
                    )
                    onClickTaskCreate(data)
                },
                isButtonEnabled = modalState.isTitleValid && modalState.isTagsValid,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ModalPreview() {
    Modal(
        modalState = ModalState(),
        onClickClose = {},
        onClickTaskCreate = {}
    )
}
