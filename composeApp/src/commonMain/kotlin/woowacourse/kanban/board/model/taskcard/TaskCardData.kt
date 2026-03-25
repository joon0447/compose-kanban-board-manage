package woowacourse.kanban.board.model.taskcard

import woowacourse.kanban.board.model.taskcard.TaskStatus

data class TaskCardData(
    val title: Title,
    val description: Description,
    val tags: Tags,
    var task: TaskStatus,
    val profile: ProfileState,
)
