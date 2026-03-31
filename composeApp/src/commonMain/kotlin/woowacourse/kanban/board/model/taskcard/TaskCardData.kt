package woowacourse.kanban.board.model.taskcard

import java.util.UUID

data class TaskCardData(
    val id: String = UUID.randomUUID().toString(),
    val taskTitle: TaskTitle,
    val taskDescription: TaskDescription,
    val taskTags: TaskTags,
    val status: Status,
    val assignee: Assignee?,
)
