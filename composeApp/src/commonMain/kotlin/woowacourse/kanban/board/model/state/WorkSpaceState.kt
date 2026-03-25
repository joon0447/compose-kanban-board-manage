package woowacourse.kanban.board.model.state

import woowacourse.kanban.board.model.project.Project
import woowacourse.kanban.board.model.taskcard.TaskStatus
import woowacourse.kanban.board.model.taskcard.TaskCardData

data class WorkSpaceState(
    val projects : List<Project>
) {
}
