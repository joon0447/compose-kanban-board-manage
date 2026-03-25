package woowacourse.kanban.board.component.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.modal.Modal
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.state.ModalState

@Composable
fun Board(
    project: Project,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    var shouldShowSnackbar by remember { mutableStateOf(false) }
    var isShowModal by remember { mutableStateOf(false) }

    val modalState = remember { ModalState() }

    LaunchedEffect(shouldShowSnackbar) {
        if (shouldShowSnackbar) {
            snackbarHostState.showSnackbar(
                message = ComponentText.BOARD_TASK_CREATE_SNACKBAR,
                withDismissAction = true,
            )
            shouldShowSnackbar = false
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues),
        ) {
            if (isShowModal) {
                Dialog(
                    onDismissRequest = { isShowModal = false },
                    properties = DialogProperties(
                        usePlatformDefaultWidth = false,
                    ),
                ) {
                    Modal(
                        modalState = modalState,
                        onClickClose = { isShowModal = false },
                        onClickTaskCreate = { task ->
                            project.addCard(task)
                            shouldShowSnackbar = true
                            isShowModal = false
                        },
                    )
                }
            }
            BoardHeader(
                doneRate = project.calculateDoneRate(),
                doneTasks = project.doneTasks.size,
                totalTasks = project.allTasksCount,
                onClickCreateTask = { isShowModal = isShowModal.not() },
            )
            TaskColumnSection(
                todoTasks = project.todoTasks,
                progressTasks = project.progressTasks,
                doneTasks = project.doneTasks,
            )
        }
    }
}
