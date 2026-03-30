package woowacourse.kanban.board.fixture

import java.util.UUID
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.TaskDescription
import woowacourse.kanban.board.model.taskcard.TaskTag
import woowacourse.kanban.board.model.taskcard.TaskTags
import woowacourse.kanban.board.model.taskcard.TaskTitle

object TaskCardDataFixture {
    fun create(
        id: String = UUID.randomUUID().toString(),
        title: String = "제목",
        taskDescription: String = "설명",
        tags: ImmutableList<TaskTag> = listOf(TaskTag("컴포넌트")).toImmutableList(),
        status: Status = Status.TODO,
        assigneeName: String = "다이노",
    ) = TaskCardData(
        id = id,
        taskTitle = TaskTitle(value = title),
        taskDescription = TaskDescription(taskDescription),
        taskTags = TaskTags(tags),
        status = status,
        assignee = Assignee(assigneeName, Res.drawable.profile)
    )
}
