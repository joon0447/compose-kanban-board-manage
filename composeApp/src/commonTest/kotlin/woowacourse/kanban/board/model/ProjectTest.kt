package woowacourse.kanban.board.model

import kotlinx.collections.immutable.immutableListOf
import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import woowacourse.kanban.board.fixture.TaskCardDataFixture
import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.Status
import woowacourse.kanban.board.model.taskcard.TaskCardData
import kotlin.test.Test
import kotlin.test.assertTrue


class ProjectTest {
    @Test
    fun `입력한 id를 가진 태스크 카드가 변경값으로 입력한 status로 변경된다`() {
        val project = Project(
            title = "테스트 프로젝트",
            initialTasks =  listOf(
                TaskCardDataFixture.create(
                    id = "테스트",
                    status = Status.TODO
                )
            ).toImmutableList()
        )
        project.updateTaskStatus("테스트", Status.PROGRESS)
        assertThat(project.todoTasks.size).isEqualTo(0)
        assertThat(project.progressTasks.size).isEqualTo(1)
    }

    @Test
    fun `찾고자 하는 태스크 카드의 id값을 넣었을 때 해당 id 값을 가진 TaskCardData를 찾을 수 있다`() {
        val task = TaskCardDataFixture.create(
            id = "테스트",
            status = Status.TODO
        )
        val project = Project(
            title = "테스트 프로젝트",
            initialTasks = immutableListOf<TaskCardData>(
                task
            )
        )
        assertTrue { project.findTaskById("테스트") == task }
    }
}