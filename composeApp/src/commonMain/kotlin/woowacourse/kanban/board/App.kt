package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import woowacourse.kanban.board.component.board.Board
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun App() {
    val workSpace = WorkSpaceState(
        listOf(
            Project("Compose1", mutableListOf()),
            Project("Compose2", mutableListOf()),
            Project("Compose3너무너무긴문장은말줄임표로표시합니다", mutableListOf()),
            )
    )
//    val project = remember { WorkSpaceState() }
//    Board(project = project)
}
