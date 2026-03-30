package woowacourse.kanban.board.model

import kotlin.test.Test
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import woowacourse.kanban.board.model.taskcard.TaskTag

class TaskTagTest {

    @Test
    fun `Tag에 빈 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            TaskTag("")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Tag에 공백으로만 이루어진 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            TaskTag("    ")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `Tag에 5자를 초과하는 값이 입력되면 예외가 발생한다`() {
        assertThatThrownBy {
            TaskTag("일이삼사오육")
        }.isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `5자 이하의 값이 isTagValid에 입력되면 true를 반환한다`() {
        assertThat(TaskTag.isTagValid("일이삼사오"))
            .isTrue
    }
}
