package woowacourse.kanban.board.model.taskcard

@JvmInline
value class Title(val value: String) {

    init {
        require(value.isNotBlank())
    }

    companion object {
        fun isTitleValid(value: String): Boolean = value.isNotBlank()
    }
}
