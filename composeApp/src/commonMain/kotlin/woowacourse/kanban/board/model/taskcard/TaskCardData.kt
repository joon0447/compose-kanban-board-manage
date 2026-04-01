package woowacourse.kanban.board.model.taskcard

import java.util.UUID

data class TaskCardData(
    val id: String = UUID.randomUUID().toString(),
    val taskTitle: TaskTitle,
    val taskDescription: TaskDescription,
    val taskTags: TaskTags,
    val status: Status,
    val assignee: Assignee?,
) {
    fun updateData(
        taskTitle: TaskTitle,
        taskDescription: TaskDescription,
        taskTags: TaskTags,
        status: Status,
        assignee: Assignee?,
    ): TaskCardData {
        return this.copy(
            taskTitle = taskTitle,
            taskDescription = taskDescription,
            taskTags = taskTags,
            status = status,
            assignee = assignee,
        )
    }
}
