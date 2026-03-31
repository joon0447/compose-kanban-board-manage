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
            workSpaceState.selectedProject?.addCard(data)
            workSpaceState.closeCreateModal()
        }
    )

    val editModalType = ModalType.Edit(
        onDelete = {

        },
        onUpdate = {

        }
    )

    LaunchedEffect(workSpaceState.shouldShowAddSnackbar) {
        if (workSpaceState.shouldShowAddSnackbar) {
            workSpaceState.snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_CREATE_SNACKBAR,
                withDismissAction = true,
            )
            workSpaceState.hideAddSnackBar()
        }
    }

    LaunchedEffect(workSpaceState.shouldShowMoveSnackbar) {
        if (workSpaceState.shouldShowMoveSnackbar) {
            workSpaceState.snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_MOVE_SNACKBAR,
                withDismissAction = true,
            )
            workSpaceState.hideMoveSnackBar()
        }
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
                modalState = modalState
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
                modalState = modalState
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
                    onShowMoveSnackBar = { workSpaceState.showMoveSnackBar() },
                    onShowCreateTaskModal = { workSpaceState.showCreateModal() },
                    onShowEditTaskModal = { taskCardData ->
                        workSpaceState.showEditModal(taskCardData)
                    }
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
