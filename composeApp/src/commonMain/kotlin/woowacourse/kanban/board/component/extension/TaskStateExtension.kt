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
import woowacourse.kanban.board.model.taskcard.TaskStatus

fun TaskStatus.toText(): String = when (this) {
    TaskStatus.TODO -> ComponentText.STATE_BUTTON_TODO
    TaskStatus.PROGRESS -> ComponentText.STATE_BUTTON_PROGRESS
    TaskStatus.DONE -> ComponentText.STATE_BUTTON_DONE
}

fun TaskStatus.toBackgroundColor(): Color = when (this) {
    TaskStatus.TODO -> Blue90
    TaskStatus.PROGRESS -> Yellow90
    TaskStatus.DONE -> Green90
}

fun TaskStatus.toBorderColor(): Color = when (this) {
    TaskStatus.TODO -> Blue70
    TaskStatus.PROGRESS -> Yellow70
    TaskStatus.DONE -> Green70
}

fun TaskStatus.toHeaderColor(): Color = when (this) {
    TaskStatus.TODO -> Blue60
    TaskStatus.PROGRESS -> Yellow60
    TaskStatus.DONE -> Green60
}
