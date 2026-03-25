package woowacourse.kanban.board.state

import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.model.state.WorkSpaceState
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.Description
import woowacourse.kanban.board.model.taskcard.ProfileState
import woowacourse.kanban.board.model.taskcard.Tag
import woowacourse.kanban.board.model.taskcard.Tags
import woowacourse.kanban.board.model.taskcard.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

class ProjectTest {
    private lateinit var project: WorkSpaceState

    @Before
    fun setUp() {
        project = WorkSpaceState(mutableListOf<TaskCardData>())
    }

    @Test
    fun `Todo TaskCardData를 추가하면 todoList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        project.addCard(data)
        assertThat(project.todoTasks).contains(data)
    }

    @Test
    fun `Progress TaskCardData를 추가하면 progressList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.PROGRESS,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        project.addCard(data)
        assertThat(project.progressTasks).contains(data)
    }

    @Test
    fun `Done TaskCardData를 추가하면 doneList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.DONE,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        project.addCard(data)
        assertThat(project.doneTasks).contains(data)
    }

    @Test
    fun `4개 업무 중 2개를 완료했을 때 완료율은 50%로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.DONE,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )

        project.addCard(task1)
        project.addCard(task1)
        project.addCard(task2)
        project.addCard(task3)

        assertThat(project.calculateDoneRate()).isEqualTo(0.50f)
    }

    @Test
    fun `진행 상태가 모두 다른 3개 업무가 등록되면 totalTasks는 3으로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.DONE,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.PROGRESS,
            profile = ProfileState("다이노",Res.drawable.profile)
        )

        project.addCard(task1)
        project.addCard(task2)
        project.addCard(task3)

        assertThat(project.allTasksCount).isEqualTo(3)
    }

    @Test
    fun `등록된 업무가 0개일 때 완료율은 0%으로 계산된다`() {
        assertThat(project.calculateDoneRate()).isEqualTo(0.0f)
    }

    @Test
    fun `3개 업무 중 0개를 완료했을 때 완료율은 0%으로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            status = Status.TODO,
            profile = ProfileState("다이노",Res.drawable.profile)
        )

        project.addCard(task1)
        project.addCard(task2)
        project.addCard(task3)

        assertThat(project.calculateDoneRate()).isEqualTo(0.0f)
    }
}
