package woowacourse.kanban.board.component.workspace

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.collections.immutable.ImmutableList
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.workspace.SnackbarType

class WorkSpaceState(
    val projects: ImmutableList<Project>,
) {
    val snackbarHostState = SnackbarHostState()
    var selectedProject by mutableStateOf(projects.firstOrNull())
    var shouldShowSnackbar by mutableStateOf<SnackbarType?>(null)
    var isShowCreateModal by mutableStateOf(false)
    var isShowEditModal by mutableStateOf(false)
    var currentEditTask by mutableStateOf<TaskCardData?>(null)

    fun showSnackBar(snackbarType: SnackbarType) {
        shouldShowSnackbar = snackbarType
    }

    fun hideSnackbar() {
        shouldShowSnackbar = null
    }

    fun showCreateModal() {
        isShowCreateModal = true
    }

    fun showEditModal(taskCardData: TaskCardData) {
        currentEditTask = taskCardData
        isShowEditModal = true
    }

    fun closeCreateModal() {
        isShowCreateModal = false
    }

    fun closeEditModal() {
        isShowEditModal = false
    }
}

@Composable
fun rememberWorkSpaceState(projects: ImmutableList<Project>): WorkSpaceState = remember { WorkSpaceState(projects) }
