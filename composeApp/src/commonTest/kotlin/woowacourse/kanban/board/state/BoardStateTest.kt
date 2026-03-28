package woowacourse.kanban.board.state

import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.component.workspace.WorkSpaceState
import woowacourse.kanban.board.fixture.TaskCardDataFixture
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData
import kotlin.test.Test

class ProjectTest {
    private lateinit var workSpace : WorkSpaceState
    private lateinit var todoTask: TaskCardData
    private lateinit var progressTask: TaskCardData
    private lateinit var doneTask: TaskCardData

    @Before
    fun setUp() {
        workSpace = WorkSpaceState(listOf<Project>(
            Project("Compose1", listOf<TaskCardData>().toImmutableList()),
            Project("Compose2", listOf<TaskCardData>().toImmutableList()),
            Project("Compose3너무너무긴문장은말줄임표로표시합니다", listOf<TaskCardData>().toImmutableList()),
        ).toImmutableList())

        todoTask = TaskCardDataFixture.create(status = Status.TODO)
        progressTask = TaskCardDataFixture.create(status = Status.PROGRESS)
        doneTask = TaskCardDataFixture.create(status = Status.DONE)
    }

    @Test
    fun `Todo TaskCardData를 추가하면 todoList에 저장된다`() {
        workSpace.projects.first().addCard(todoTask)
        assertThat(workSpace.projects.first().todoTasks).contains(todoTask)
    }

    @Test
    fun `Progress TaskCardData를 추가하면 progressList에 저장된다`() {
        workSpace.projects.first().addCard(progressTask)
        assertThat(workSpace.projects.first().progressTasks).contains(progressTask)
    }

    @Test
    fun `Done TaskCardData를 추가하면 doneList에 저장된다`() {
        workSpace.projects.first().addCard(doneTask)
        assertThat(workSpace.projects.first().doneTasks).contains(doneTask)
    }

    @Test
    fun `4개 업무 중 2개를 완료했을 때 완료율은 50%로 계산된다`() {
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.DONE))
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.DONE))
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.PROGRESS))
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.PROGRESS))

        assertThat(workSpace.projects.first().calculateDoneRate()).isEqualTo(0.50f)
    }

    @Test
    fun `진행 상태가 모두 다른 3개 업무가 등록되면 totalTasks는 3으로 계산된다`() {
        workSpace.projects.first().addCard(todoTask)
        workSpace.projects.first().addCard(doneTask)
        workSpace.projects.first().addCard(progressTask)
        assertThat(workSpace.projects.first().allTasksCount).isEqualTo(3)
    }

    @Test
    fun `등록된 업무가 0개일 때 완료율은 0%으로 계산된다`() {
        assertThat(workSpace.projects.first().calculateDoneRate()).isEqualTo(0.0f)
    }

    @Test
    fun `3개 업무 중 0개를 완료했을 때 완료율은 0%으로 계산된다`() {
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.PROGRESS))
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.PROGRESS))
        workSpace.projects.first().addCard(TaskCardDataFixture.create(status = Status.TODO))

        assertThat(workSpace.projects.first().calculateDoneRate()).isEqualTo(0.0f)
    }
}
