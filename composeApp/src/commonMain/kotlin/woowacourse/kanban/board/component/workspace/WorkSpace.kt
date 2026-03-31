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
                onClickClose = { workSpaceState.closeCreateModal() },
                onClickTaskCreate = { task ->
                    workSpaceState.onTaskAdded(task)
                },
                modalType = ModalType.Create
            )
        }
    }

    if (workSpaceState.isShowEditModal) {
        Dialog(
            onDismissRequest = { workSpaceState.closeCreateModal() },
            properties = DialogProperties(
                usePlatformDefaultWidth = false,
            ),
        ) {
            Modal(
                assignees = assignees,
                onClickClose = { workSpaceState.closeEditModal() },
                onClickTaskCreate = { task ->
                    workSpaceState.onTaskAdded(task)
                },
                modalType = ModalType.Edit(
                    onUpdate = {},
                    onDelete = {},
                )
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
