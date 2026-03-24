package woowacourse.kanban.board.component.extension

import androidx.compose.ui.graphics.Color
import woowacourse.kanban.board.Blue60
import woowacourse.kanban.board.Blue70
import woowacourse.kanban.board.Blue90
import woowacourse.kanban.board.Green60
import woowacourse.kanban.board.Green70
import woowacourse.kanban.board.Green90
import woowacourse.kanban.board.Yellow60
import woowacourse.kanban.board.Yellow70
import woowacourse.kanban.board.Yellow90
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.model.TaskState

fun TaskState.toText(): String = when (this) {
    TaskState.TODO -> ComponentText.STATE_BUTTON_TODO
    TaskState.PROGRESS -> ComponentText.STATE_BUTTON_PROGRESS
    TaskState.DONE -> ComponentText.STATE_BUTTON_DONE
}

fun TaskState.toBackgroundColor(): Color = when (this) {
    TaskState.TODO -> Blue90
    TaskState.PROGRESS -> Yellow90
    TaskState.DONE -> Green90
}

fun TaskState.toBorderColor(): Color = when (this) {
    TaskState.TODO -> Blue70
    TaskState.PROGRESS -> Yellow70
    TaskState.DONE -> Green70
}

fun TaskState.toHeaderColor(): Color = when (this) {
    TaskState.TODO -> Blue60
    TaskState.PROGRESS -> Yellow60
    TaskState.DONE -> Green60
}
