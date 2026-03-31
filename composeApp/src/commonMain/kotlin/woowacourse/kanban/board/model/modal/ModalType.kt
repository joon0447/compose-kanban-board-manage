package woowacourse.kanban.board.model.modal

sealed class ModalType {
    object Create : ModalType()

    data class Edit(
        val onDelete: () -> Unit,
        val onUpdate: () -> Unit,
    ) : ModalType()
}
