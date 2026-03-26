package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.WorkSpace
import woowacourse.kanban.board.component.board.Board
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.Profile
import woowacourse.kanban.board.model.taskcard.TaskCardData
import java.util.UUID

@Composable
fun App() {
    val workSpace = WorkSpaceState(
        listOf(
            Project(title = "Compose1", id = UUID.randomUUID().toString()),
            Project(title = "Compose1", id = UUID.randomUUID().toString()),
            Project(title = "Compose3너무너무긴문장은말줄임표로표시합니다", id = UUID.randomUUID().toString()),
            ).toImmutableList()
    )
    WorkSpace(workSpaceState = workSpace)
}
