package woowacourse.kanban.board.component.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.modal.Modal
import woowacourse.kanban.board.component.state.rememberBoardState

@Composable
fun Board(
    modifier: Modifier = Modifier,
) {
    val boardState = rememberBoardState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(boardState.shouldShowSnackbar) {
        if (boardState.shouldShowSnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_CREATE_SNACKBAR,
                withDismissAction = true,
            )
            boardState.shouldShowSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues),
        ) {
            if (boardState.isShowModal) {
                Dialog(
                    onDismissRequest = { boardState.isShowModal = false },
                    properties = DialogProperties(
                        usePlatformDefaultWidth = false,
                    ),
                ) {
                    Modal(
                        onClickClose = { boardState.isShowModal = false },
                        onClickTaskCreate = { task ->
                            boardState.addCard(task)
                            boardState.shouldShowSnackbar = true
                            boardState.isShowModal = false
                        },
                    )
                }
            }
            BoardHeader(
                doneRate = boardState.calculateDoneRate(),
                doneTasks = boardState.doneTasks.size,
                totalTasks = boardState.allTasksCount,
                onClickCreateTask = { boardState.toggleShowModal() },
            )
            TaskColumnSection(
                todoTasks = boardState.todoTasks,
                progressTasks = boardState.progressTasks,
                doneTasks = boardState.doneTasks,
            )
        }
    }
}
