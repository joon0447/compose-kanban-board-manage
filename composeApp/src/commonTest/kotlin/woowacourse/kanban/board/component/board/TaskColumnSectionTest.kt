package woowacourse.kanban.board.component.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.model.project.Project
import kotlin.test.Test
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskDescription
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.taskcard.TaskTag
import woowacourse.kanban.board.model.taskcard.TaskTags
import woowacourse.kanban.board.model.taskcard.TaskTitle
import woowacourse.kanban.board.model.taskcard.TaskCardData

@OptIn(ExperimentalTestApi::class)
class TaskColumnSectionTest {

    @Test
    fun `todoTasks에 등록된 태스크가 3개면 3이 출력된다`() = runComposeUiTest {
        val data1 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data2 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data3 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.TODO,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val todoTasks = listOf(data1, data2, data3)
        val project = Project(
            title = "title",
            initialTasks = todoTasks.toImmutableList()
        )
        setContent {
            TaskColumnSection(
                project = project,
                onMoveSnackBar = {}
            )
        }

        onNodeWithText("3").assertIsDisplayed()
    }

    @Test
    fun `progressTasks에 등록된 태스크가 5개면 5가 출력된다`() = runComposeUiTest {
        val data1 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data2 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data3 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data4 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data5 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.PROGRESS,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val progressTasks = listOf(data1, data2, data3, data4, data5)
        val project = Project(
            title = "title",
            initialTasks = progressTasks.toImmutableList()
        )
        setContent {
            TaskColumnSection(
                project = project,
                onMoveSnackBar = {}
            )
        }

        onNodeWithText("5").assertIsDisplayed()
    }

    @Test
    fun `doneTasks에 등록된 태스크가 4개면 4가 출력된다`() = runComposeUiTest {
        val data1 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data2 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data3 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val data4 = TaskCardData(
            taskTitle = TaskTitle(value = "제목"),
            taskDescription = TaskDescription("설명"),
            taskTags = TaskTags(listOf(TaskTag("컴포넌트")).toImmutableList()),
            status = Status.DONE,
            assignee = Assignee("다이노",Res.drawable.profile)
        )
        val doneTasks = listOf(data1, data2, data3, data4)
        val project = Project(
            title = "title",
            initialTasks = doneTasks.toImmutableList()
        )
        setContent {
            TaskColumnSection(
                project = project,
                onMoveSnackBar = {}
            )
        }

        onNodeWithText("4").assertIsDisplayed()
    }
}
