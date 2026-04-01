package woowacourse.kanban.board.model

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.fixture.TaskCardDataFixture
import woowacourse.kanban.board.model.taskcard.Assignee
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.TaskDescription
import woowacourse.kanban.board.model.taskcard.TaskTag
import woowacourse.kanban.board.model.taskcard.TaskTags
import woowacourse.kanban.board.model.taskcard.TaskTitle
import kotlin.test.Test

class TaskCardDataTest {

    private lateinit var todoTask: TaskCardData

    @Before
    fun setUp() {
        todoTask = TaskCardDataFixture.create(status = Status.TODO)
    }

    @Test
    fun `TaskCardData의 상태를 변경할 수 있다`() {
        val updatedData = todoTask.updateTaskStatus(Status.PROGRESS)
        assertThat(updatedData.status).isEqualTo(Status.PROGRESS)
    }

    @Test
    fun `TaskCardData의 taskTitle을 수정할 수 있다`() {
        val updateTaskCardData = TaskCardData(
            taskTitle = TaskTitle("수정수정"),
            taskDescription = todoTask.taskDescription,
            taskTags = todoTask.taskTags,
            status = todoTask.status,
            assignee = todoTask.assignee,
        )
        val updatedData = todoTask.updateData(updateTaskCardData)
        assertThat(updatedData.taskTitle).isEqualTo(TaskTitle("수정수정"))
    }

    @Test
    fun `TaskCardData의 taskDescription을 수정할 수 있다`() {
        val updateTaskCardData = TaskCardData(
            taskTitle = todoTask.taskTitle,
            taskDescription = TaskDescription("설명수정했어요"),
            taskTags = todoTask.taskTags,
            status = todoTask.status,
            assignee = todoTask.assignee,
        )
        val updatedData = todoTask.updateData(updateTaskCardData)
        assertThat(updatedData.taskDescription).isEqualTo(TaskDescription("설명수정했어요"))
    }

    @Test
    fun `TaskCardData의 taskTags를 수정할 수 있다`() {
        val updatedTags = TaskTags(listOf(TaskTag("d")).toImmutableList())
        val updateTaskCardData = TaskCardData(
            taskTitle = todoTask.taskTitle,
            taskDescription = todoTask.taskDescription,
            taskTags = updatedTags,
            status = todoTask.status,
            assignee = todoTask.assignee,
        )
        val updatedData = todoTask.updateData(updateTaskCardData)
        assertThat(updatedData.taskTags).isEqualTo(updatedTags)
    }

    @Test
    fun `TaskCardData의 status를 수정할 수 있다`() {
        val updateTaskCardData = TaskCardData(
            taskTitle = todoTask.taskTitle,
            taskDescription = todoTask.taskDescription,
            taskTags = todoTask.taskTags,
            status = Status.PROGRESS,
            assignee = todoTask.assignee,
        )
        val updatedData = todoTask.updateData(updateTaskCardData)
        assertThat(updatedData.status).isEqualTo(Status.PROGRESS)
    }

    @Test
    fun `TaskCardData의 assignee를 수정할 수 있다`() {
        val newAssignee = Assignee("호이", Res.drawable.profile)
        val updateTaskCardData = TaskCardData(
            taskTitle = todoTask.taskTitle,
            taskDescription = TaskDescription("설명수정했어요"),
            taskTags = todoTask.taskTags,
            status = todoTask.status,
            assignee = newAssignee,
        )
        val updatedData = todoTask.updateData(updateTaskCardData)
        assertThat(updatedData.assignee).isEqualTo(newAssignee)
    }
}
