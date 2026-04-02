package woowacourse.kanban.board.model.modal

sealed class ModalType {
    class Create(
        val onCreate: () -> Unit,
    ) : ModalType()

    class Edit(
        val onDelete: () -> Unit,
        val onUpdate: () -> Unit,
    ) : ModalType()
}
