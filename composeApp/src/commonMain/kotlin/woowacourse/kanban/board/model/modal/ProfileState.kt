package woowacourse.kanban.board.model.modal

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import org.jetbrains.compose.resources.DrawableResource

enum class ProfileState(
    val nickname: String,
    val icon: DrawableResource,
) {
    DINO(nickname = "다이노", icon = Res.drawable.profile),
    PAMES(nickname = "페임스", icon = Res.drawable.profile),
}
