package woowacourse.kanban.board.state

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.component.state.BoardState
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.modal.Description
import woowacourse.kanban.board.model.modal.ProfileState
import woowacourse.kanban.board.model.modal.Tag
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

class BoardStateTest {
    private lateinit var boardState: BoardState

    @Before
    fun setUp() {
        boardState = BoardState()
    }

    @Test
    fun `Todo TaskCardData를 추가하면 todoList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )
        boardState.addCard(data)
        assertThat(boardState.todoTasks).contains(data)
    }

    @Test
    fun `Progress TaskCardData를 추가하면 progressList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO
        )
        boardState.addCard(data)
        assertThat(boardState.progressTasks).contains(data)
    }

    @Test
    fun `Done TaskCardData를 추가하면 doneList에 저장된다`() {
        val data = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.DONE,
            profile = ProfileState.DINO
        )
        boardState.addCard(data)
        assertThat(boardState.doneTasks).contains(data)
    }

    @Test
    fun `4개 업무 중 2개를 완료했을 때 완료율은 50%로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.DONE,
            profile = ProfileState.DINO
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )

        boardState.addCard(task1)
        boardState.addCard(task1)
        boardState.addCard(task2)
        boardState.addCard(task3)

        assertThat(boardState.calculateDoneRate()).isEqualTo(0.50f)
    }

    @Test
    fun `진행 상태가 모두 다른 3개 업무가 등록되면 totalTasks는 3으로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.DONE,
            profile = ProfileState.DINO
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO
        )

        boardState.addCard(task1)
        boardState.addCard(task2)
        boardState.addCard(task3)

        assertThat(boardState.allTasksCount).isEqualTo(3)
    }

    @Test
    fun `등록된 업무가 0개일 때 완료율은 0%으로 계산된다`() {
        assertThat(boardState.calculateDoneRate()).isEqualTo(0.0f)
    }

    @Test
    fun `3개 업무 중 0개를 완료했을 때 완료율은 0%으로 계산된다`() {
        val task1 = TaskCardData(
            title = Title(value = "업무1"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )
        val task2 = TaskCardData(
            title = Title(value = "업무2"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )
        val task3 = TaskCardData(
            title = Title(value = "업무3"),
            description = Description(""),
            tags = Tags(value = listOf(Tag("컴포넌트"))),
            task = TaskState.TODO,
            profile = ProfileState.DINO
        )

        boardState.addCard(task1)
        boardState.addCard(task2)
        boardState.addCard(task3)

        assertThat(boardState.calculateDoneRate()).isEqualTo(0.0f)
    }
}
