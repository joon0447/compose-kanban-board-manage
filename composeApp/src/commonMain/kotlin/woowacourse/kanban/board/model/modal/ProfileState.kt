package woowacourse.kanban.board.model.modal

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.DrawableResource

data class ProfileState(
    val nickname: String,
    val icon: DrawableResource,
)