package woowacourse.kanban.board.model.taskcard

import woowacourse.kanban.board.model.project.MoveResult
import java.util.UUID

data class TaskCardData(
    val id: String = UUID.randomUUID().toString(),
    val taskTitle: TaskTitle,
    val taskDescription: TaskDescription,
    val taskTags: TaskTags,
    val status: Status,
    val assignee: Assignee?,
) {
    fun isTaskStatusUpdateAvailable(targetStatus: Status): MoveResult {
        val availableUpdateStatuses = this.status.availableUpdateStatuses()
        if (availableUpdateStatuses.contains(targetStatus)) {
            if (targetStatus == Status.PROGRESS && this.assignee == null) return MoveResult.NO_ASSIGNEE
            return MoveResult.SUCCESS
        }
        return MoveResult.INVALID_MOVE
    }

    fun updateTaskStatus(targetStatus: Status): TaskCardData {
        return this.copy(
            status = targetStatus
        )
    }

    fun updateData(updateTaskCardData: TaskCardData): TaskCardData {
        return this.copy(
            taskTitle = updateTaskCardData.taskTitle,
            taskDescription = updateTaskCardData.taskDescription,
            taskTags = updateTaskCardData.taskTags,
            status = updateTaskCardData.status,
            assignee = updateTaskCardData.assignee,
        )
    }
}
