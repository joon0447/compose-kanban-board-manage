package woowacourse.kanban.board.model.modal

data class Title(val value: String) {

    init {
        require(value.isNotBlank())
    }

    companion object {
        fun isTitleValid(value: String): Boolean = value.isNotBlank()
    }
}
