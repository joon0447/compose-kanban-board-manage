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
    fun updateTaskStatus(targetStatus: Status): TaskCardData {
        return this.copy(
            status = targetStatus
        )
    }

    fun updateData(taskCardData: TaskCardData): TaskCardData {
        return this.copy(
            taskTitle = taskCardData.taskTitle,
            taskDescription = taskCardData.taskDescription,
            taskTags = taskCardData.taskTags,
            status = taskCardData.status,
            assignee = taskCardData.assignee,
        )
    }
}
