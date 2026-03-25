package woowacourse.kanban.board.model.state

import woowacourse.kanban.board.model.project.Project

data class WorkSpaceState(
    val projects : List<Project>
) {
}
