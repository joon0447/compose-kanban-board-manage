package woowacourse.kanban.board.model.taskcard

import kotlinx.collections.immutable.ImmutableList

data class TaskTags(val value: ImmutableList<TaskTag>) {

    init {
        require(value.size <= MAX_TAGS)
    }

    companion object {
        private const val MAX_TAGS = 5

        fun isTagsValid(value: List<TaskTag>): Boolean {
            return value.size <= MAX_TAGS
        }
    }
}
