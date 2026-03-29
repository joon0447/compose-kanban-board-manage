package woowacourse.kanban.board.component.workspace

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.project.Project

class WorkSpaceState(
    val projects: ImmutableList<Project>,
) {
    var selectedProject by mutableStateOf(projects.firstOrNull())
}

@Composable
fun rememberWorkSpaceState(projects: ImmutableList<Project>): WorkSpaceState = remember { WorkSpaceState(projects) }
