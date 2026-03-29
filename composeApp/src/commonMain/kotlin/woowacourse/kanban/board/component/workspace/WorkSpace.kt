package woowacourse.kanban.board.component.workspace

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.component.board.Board
import woowacourse.kanban.board.component.sample.ProfilePreviewData
import woowacourse.kanban.board.component.sample.ProjectPreviewData
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Assignee

@Composable
fun WorkSpace(
    projects: ImmutableList<Project>,
    assignees: ImmutableList<Assignee>,
    modifier: Modifier = Modifier,
) {
    val workSpaceState = rememberWorkSpaceState(projects)
    workSpaceState.selectedProject?.let { selectedProject ->
        Row(
            modifier = modifier,
        ) {
            SideBar(
                projects = workSpaceState.projects,
                selectedProject = selectedProject,
                onChangeProject = { workSpaceState.selectedProject = it },
            )
            Board(
                project = selectedProject,
                assignees = assignees,
            )
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
            assignees = assignees)
    }
}
