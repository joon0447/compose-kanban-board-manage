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
    var shouldShowMoveSuccessSnackbar by mutableStateOf(false)
    var shouldShowMoveFailedSnackbar by mutableStateOf(false)
    var shouldShowNoAssigneeMoveSnackbar by mutableStateOf(false)
    var shouldShowDeleteSuccessSnackbar by mutableStateOf(false)
    var shouldShowDeleteFailedSnackbar by mutableStateOf(false)
    var shouldShowEditSnackbar by mutableStateOf(false)
    var isShowCreateModal by mutableStateOf(false)
    var isShowEditModal by mutableStateOf(false)
    var currentEditTask by mutableStateOf<TaskCardData?>(null)

    fun showAddSnackBar() {
        shouldShowAddSnackbar = true
    }

    fun hideAddSnackBar() {
        shouldShowAddSnackbar = false
    }

    fun showMoveSuccessSnackBar() {
        shouldShowMoveSuccessSnackbar = true
    }

    fun hideMoveSuccessSnackBar() {
        shouldShowMoveSuccessSnackbar = false
    }

    fun showMoveFailedSnackBar() {
        shouldShowMoveFailedSnackbar = true
    }

    fun hideMoveFailedSnackBar() {
        shouldShowMoveFailedSnackbar = false
    }

    fun showDeleteSuccessSnackBar() {
        shouldShowDeleteSuccessSnackbar = true
    }

    fun hideDeleteSuccessSnackBar() {
        shouldShowDeleteSuccessSnackbar = false
    }

    fun showDeleteFailedSnackBar() {
        shouldShowDeleteFailedSnackbar = true
    }

    fun hideDeleteFailedSnackBar() {
        shouldShowDeleteFailedSnackbar = false
    }

    fun showNoAssigneeMoveSnackBar() {
        shouldShowNoAssigneeMoveSnackbar = true
    }

    fun hideNoAssigneeMoveSnackBar() {
        shouldShowNoAssigneeMoveSnackbar = false
    }

    fun showEditSnackBar() {
        shouldShowEditSnackbar = true
    }

    fun hideEditSnackBar() {
        shouldShowEditSnackbar = false
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
