package woowacourse.kanban.board.model.modal

sealed class ModalType {
    data class Create(
        val onCreate: () -> Unit,
    ) : ModalType()

    data class Edit(
        val onDelete: () -> Unit,
        val onUpdate: () -> Unit,
    ) : ModalType()
}
