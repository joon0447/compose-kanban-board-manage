package woowacourse.kanban.board.model.state

import woowacourse.kanban.board.model.taskcard.TaskStatus
import woowacourse.kanban.board.model.taskcard.TaskCardData

class BoardState {
    private val tasks = mutableListOf<TaskCardData>()
    val allTasksCount get() = tasks.size
    val todoTasks get() = tasks.filter { it.task == TaskStatus.TODO }
    val progressTasks get() = tasks.filter { it.task == TaskStatus.PROGRESS }
    val doneTasks get() = tasks.filter { it.task == TaskStatus.DONE }


    fun addCard(data: TaskCardData) = tasks.add(data)

    fun calculateDoneRate(): Float {
        val totalTasks = todoTasks.size + progressTasks.size + doneTasks.size
        if (totalTasks == 0) return 0f
        return doneTasks.size.toFloat() / totalTasks.toFloat()
    }
}
