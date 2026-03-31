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

class WorkSpaceState(
    val projects: ImmutableList<Project>,
) {
    val snackbarHostState = SnackbarHostState()
    var selectedProject by mutableStateOf(projects.firstOrNull())
    var shouldShowAddSnackbar by mutableStateOf(false)
    var shouldShowMoveSnackbar by mutableStateOf(false)
    var isShowCreateModal by mutableStateOf(false)
    var isShowEditModal by mutableStateOf(false)

    fun showAddSnackBar() {
        shouldShowAddSnackbar = true
    }

    fun hideAddSnackBar() {
        shouldShowAddSnackbar = false
    }

    fun showMoveSnackBar() {
        shouldShowMoveSnackbar = true
    }

    fun hideMoveSnackBar() {
        shouldShowMoveSnackbar = false
    }

    fun showCreateModal() {
        isShowCreateModal = true
    }

    fun showEditModal() {
        isShowEditModal = true
    }

    fun closeCreateModal() {
        isShowCreateModal = false
    }

    fun closeEditModal() {
        isShowEditModal = false
    }

    fun onTaskAdded(task: TaskCardData) {
        val project = selectedProject ?: return
        project.addCard(task)
        showAddSnackBar()
        closeCreateModal()
    }
}

@Composable
fun rememberWorkSpaceState(projects: ImmutableList<Project>): WorkSpaceState = remember { WorkSpaceState(projects) }
