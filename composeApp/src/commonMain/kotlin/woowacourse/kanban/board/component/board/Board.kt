package woowacourse.kanban.board.component.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
import woowacourse.kanban.board.Gray80
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.modal.Modal
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Assignee

@Composable
fun Board(
    project: Project,
    assignees: ImmutableList<Assignee>,
    modifier: Modifier = Modifier,
) {
    val boardState = rememberBoardState()


    LaunchedEffect(boardState.shouldShowSnackbar) {
        if (boardState.shouldShowSnackbar) {
            boardState.snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_CREATE_SNACKBAR,
                withDismissAction = true,
            )
            boardState.shouldShowSnackbar = false
        }
    }

    LaunchedEffect(boardState.shouldShowMoveSnackbar) {
        if (boardState.shouldShowMoveSnackbar) {
            boardState.snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_MOVE_SNACKBAR,
                withDismissAction = true,
            )
            boardState.shouldShowMoveSnackbar = false
        }
    }


    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = boardState.snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Gray80),
        ) {
            if (boardState.isShowModal) {
                Dialog(
                    onDismissRequest = { boardState.isShowModal = false },
                    properties = DialogProperties(
                        usePlatformDefaultWidth = false,
                    ),
                ) {
                    Modal(
                        assignees = assignees,
                        onClickClose = { boardState.isShowModal = false },
                        onClickTaskCreate = { task ->
                            project.addCard(task)
                            boardState.showSnackBar()
                            boardState.closeModal()
                        },
                    )
                }
            }
            BoardHeader(
                title = project.title,
                doneRate = project.calculateDoneRate(),
                doneTasks = project.doneTasks.size,
                totalTasks = project.allTasksCount,
                onClickCreateTask = { boardState.isShowModal = boardState.isShowModal.not() },
            )
            TaskColumnSection(
                project = project,
                onMoveSnackBar = { boardState.shouldShowMoveSnackbar = true },
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun BoardPreview() {
    val project = ProjectPreviewData().values.toMutableList()[0]
    val profiles = ProfilePreviewData().values.toImmutableList()
    MaterialTheme {
        Board(project, profiles)
    }
}
