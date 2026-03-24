package woowacourse.kanban.board.component.board

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.runComposeUiTest
import kotlin.test.Test
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.modal.Description
import woowacourse.kanban.board.model.modal.ProfileState
import woowacourse.kanban.board.model.modal.Tag
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

@OptIn(ExperimentalTestApi::class)
class TaskColumnSectionTest {

    @Test
    fun `todoTasks에 등록된 태스크가 3개면 3이 출력된다`() = runComposeUiTest {
        val data = TaskCardData(
            title = Title(value = "제목"),
            description = Description("설명"),
            tags = Tags(listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
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
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO
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
            task = TaskState.DONE,
            profile = ProfileState.DINO
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
