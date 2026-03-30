package woowacourse.kanban.board.model.taskcard

import org.jetbrains.compose.resources.DrawableResource

data class Assignee(
    val nickname: String,
    val icon: DrawableResource,
)
