package woowacourse.kanban.board.component.modal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskTag
import woowacourse.kanban.board.model.taskcard.TaskTags
import woowacourse.kanban.board.model.taskcard.TaskTitle

class ModalState(
    assignees: ImmutableList<Assignee>
) {
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var tags by mutableStateOf("")
    var status by mutableStateOf(Status.TODO)
    var assignees by mutableStateOf(assignees.first())

    val isTaskTitleValid by derivedStateOf { TaskTitle.Companion.isTitleValid(title) }
    val isTaskTagsValid by derivedStateOf {
        TaskTag.Companion.isTagValid(tags) && TaskTags.Companion.isTagsValid(
            TaskTag.Companion.extractedTags(
                tags
            )
        )
    }
}

@Composable
fun rememberModalState(assignees: ImmutableList<Assignee>): ModalState = remember { ModalState(assignees) }