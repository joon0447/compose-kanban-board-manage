package woowacourse.kanban.board.component.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import woowacourse.kanban.board.Gray80
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.project.Project

@Composable
fun Board(
    project: Project,
    onShowMoveSnackBar: () -> Unit,
    onShowCreateTaskModal: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(Gray80),
    ) {
        BoardHeader(
            title = project.title,
            doneRate = project.calculateDoneRate(),
            doneTasks = project.doneTasks.size,
            totalTasks = project.allTasksCount,
            onClickCreateTask = { onShowCreateTaskModal() },
        )
        TaskColumnSection(
            project = project,
            onMoveSnackBar = { onShowMoveSnackBar() },
        )
    }
}

@Preview(showBackground = true, widthDp = 1000)
@Composable
private fun BoardPreview() {
    val project = ProjectPreviewData().values.toMutableList()[0]
    MaterialTheme {
        Board(
            project = project,
            onShowMoveSnackBar = { },
            onShowCreateTaskModal = { },
        )
    }
}
