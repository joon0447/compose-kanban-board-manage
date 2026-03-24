package woowacourse.kanban.board.model.modal

data class Tags(val value: List<Tag>) {

    init {
        require(value.size <= MAX_TAGS)
    }

    companion object {
        private const val MAX_TAGS = 5

        fun isTagsValid(value: List<Tag>): Boolean {
            return value.size <= MAX_TAGS
        }
    }
}
