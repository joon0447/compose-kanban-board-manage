package woowacourse.kanban.board.component.sample

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import woowacourse.kanban.board.model.taskcard.Profile

class ProfilePreviewData: PreviewParameterProvider<Profile> {
    override val values: Sequence<Profile> = sequenceOf(
        Profile("다이노", Res.drawable.profile),
        Profile("페임스", Res.drawable.profile)
    )
}