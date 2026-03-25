package woowacourse.kanban.board.component.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlin.test.Test
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.ProfileState
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

@OptIn(ExperimentalTestApi::class)
class TaskColumnSectionTest {

    @Test
    fun `todoTasks에 등록된 태스크가 3개면 3이 출력된다`() = runComposeUiTest {
        val data = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val todoTasks = listOf(data, data, data)
        setContent {
            TaskColumnSection(
                todoTasks = todoTasks,
                progressTasks = emptyList(),
                doneTasks = emptyList()
            )
        }

        onNodeWithText("3").assertIsDisplayed()
    }

    @Test
    fun `progressTasks에 등록된 태스크가 5개면 5가 출력된다`() = runComposeUiTest {
        val data = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트"))),
            status = Status.PROGRESS,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val progressTasks = listOf(data, data, data, data, data)
        setContent {
            TaskColumnSection(
                todoTasks = emptyList(),
                progressTasks = progressTasks,
                doneTasks = emptyList()
            )
        }

        onNodeWithText("5").assertIsDisplayed()
    }

    @Test
    fun `doneTasks에 등록된 태스크가 4개면 4가 출력된다`() = runComposeUiTest {
        val data = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트"))),
            status = Status.DONE,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val doneTasks = listOf(data, data, data, data)
        setContent {
            TaskColumnSection(
                todoTasks = emptyList(),
                progressTasks = emptyList(),
                doneTasks = doneTasks
            )
        }

        onNodeWithText("4").assertIsDisplayed()
    }
}
