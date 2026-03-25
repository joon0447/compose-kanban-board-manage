package woowacourse.kanban.board.model.project

import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.TaskStatus

data class Project(
    val title: String,
    val tasks: MutableList<TaskCardData>
) {
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