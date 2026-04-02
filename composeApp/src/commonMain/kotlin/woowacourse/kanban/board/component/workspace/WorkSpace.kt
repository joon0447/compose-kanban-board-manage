package woowacourse.kanban.board.component.workspace

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.board.Board
import woowacourse.kanban.board.component.modal.Modal
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.modal.ModalType
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.workspace.SnackbarType

@Composable
fun WorkSpace(
    projects: ImmutableList<Project>,
    assignees: ImmutableList<Assignee>,
    modifier: Modifier = Modifier,
) {
    val workSpaceState = rememberWorkSpaceState(projects)
    val modalState = rememberModalState(assignees)

    val createModalType = ModalType.Create(
        onCreate = {
            val data = modalState.toTaskCardData()
            workSpaceState.selectedProject?.addTask(data)
            workSpaceState.closeCreateModal()
            workSpaceState.showSnackBar(SnackbarType.ADD)
        },
    )

    val editModalType = ModalType.Edit(
        onDelete = {
            val taskId = workSpaceState.currentEditTask?.id
            val deleteResult = workSpaceState.selectedProject?.deleteTaskById(taskId) ?: false
            workSpaceState.closeEditModal()
            if (deleteResult) workSpaceState.showSnackBar(SnackbarType.DELETE_SUCCESS)
            else workSpaceState.showSnackBar(SnackbarType.DELETE_FAILED)
        },
        onUpdate = {
            val taskId = workSpaceState.currentEditTask?.id
            val data = modalState.toTaskCardData()
            if (taskId != null) {
                workSpaceState.selectedProject?.tryUpdateTaskData(
                    id = taskId,
                    updateTaskCardData = data,
                )
            }
            workSpaceState.closeEditModal()
            workSpaceState.showSnackBar(SnackbarType.EDIT)
        },
    )

    LaunchedEffect(workSpaceState.shouldShowSnackbar) {
        val snackbarText = when (workSpaceState.shouldShowSnackbar) {
            null -> return@LaunchedEffect
            SnackbarType.ADD -> ComponentText.BOARD_TASK_CREATE_SNACKBAR
            SnackbarType.EDIT -> ComponentText.BOARD_TASK_EDIT_SNACKBAR
            SnackbarType.MOVE_SUCCESS -> ComponentText.BOARD_TASK_MOVE_SUCCESS_SNACKBAR
            SnackbarType.MOVE_FAILED -> ComponentText.BOARD_TASK_MOVE_FAILED_SNACKBAR
            SnackbarType.MOVE_NO_ASSIGNEE -> ComponentText.BOARD_TASK_MOVE_NO_ASSIGNEE_SNACKBAR
            SnackbarType.DELETE_SUCCESS -> ComponentText.BOARD_TASK_DELETE_SUCCESS_SNACKBAR
            SnackbarType.DELETE_FAILED -> ComponentText.BOARD_TASK_DELETE_FAILED_SNACKBAR
        }
        workSpaceState.snackbarHostState.showSnackbar(
            message = snackbarText,
            withDismissAction = true,
        )
        workSpaceState.hideSnackbar()
    }

    if (workSpaceState.isShowCreateModal) {
        Dialog(
            onDismissRequest = { workSpaceState.closeCreateModal() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
            ),
        ) {
            Modal(
                assignees = assignees,
                onClickClose = {
                    workSpaceState.closeCreateModal()
                },
                modalType = createModalType,
                modalState = modalState,
            )
        }
    }

    if (workSpaceState.isShowEditModal) {
        val taskCardData = workSpaceState.currentEditTask
        Dialog(
            onDismissRequest = { workSpaceState.closeCreateModal() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
            ),
        ) {
            Modal(
                data = taskCardData,
                assignees = assignees,
                onClickClose = {
                    workSpaceState.closeEditModal()
                },
                modalType = editModalType,
                modalState = modalState,
            )
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = workSpaceState.snackbarHostState) },
    ) { paddingValues ->
        workSpaceState.selectedProject?.let { selectedProject ->
            Row(
                modifier = Modifier
                    .padding(paddingValues),
            ) {
                SideBar(
                    projects = workSpaceState.projects,
                    selectedProject = selectedProject,
                    onChangeProject = { workSpaceState.selectedProject = it },
                )
                Board(
                    project = selectedProject,
                    onShowMoveSuccessSnackBar = { workSpaceState.showSnackBar(SnackbarType.MOVE_SUCCESS) },
                    onShowMoveFailedSnackbar = { workSpaceState.showSnackBar(SnackbarType.MOVE_FAILED) },
                    onShowCreateTaskModal = { workSpaceState.showCreateModal() },
                    onShowEditTaskModal = { taskCardData ->
                        workSpaceState.showEditModal(taskCardData)
                    },
                    onShowNoAssigneeSnackbar = { workSpaceState.showSnackBar(SnackbarType.MOVE_NO_ASSIGNEE) },
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 1500)
@Composable
private fun WorkSpacePreview() {
    val assignees = ProfilePreviewData().values.toImmutableList()
    MaterialTheme {
        WorkSpace(
            projects = ProjectPreviewData().values.toImmutableList(),
            assignees = assignees,
        )
    }
}
