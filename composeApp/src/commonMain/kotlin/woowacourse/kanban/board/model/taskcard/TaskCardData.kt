package woowacourse.kanban.board.model.taskcard

data class TaskCardData(
    val title: Title,
    val description: Description,
    val tags: Tags,
    val status: Status,
    val profile: ProfileState,
)
