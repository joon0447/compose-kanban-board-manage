package woowacourse.kanban.board.model.taskcard

data class TaskTag(
    val value: String
) {

    init {
        require(value.isNotBlank())
        require(value.length <= TAG_MAX_TEXT_LENGTH)
    }

    companion object {
        private const val TAG_MAX_TEXT_LENGTH = 5

        fun isTagValid(value: String): Boolean {
            if (value.isEmpty()) return true
            val splitTags = value.split(",").map { it.trim() }
            return splitTags.all { it.isNotEmpty() && it.length <= TAG_MAX_TEXT_LENGTH }
        }

        fun extractedTags(value: String): List<TaskTag> =
            value.split(",")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { TaskTag(it) }
    }
}
