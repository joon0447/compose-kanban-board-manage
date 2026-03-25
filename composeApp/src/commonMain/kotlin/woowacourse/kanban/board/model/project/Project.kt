package woowacourse.kanban.board.model.project

import androidx.compose.runtime.mutableStateListOf
import woowacourse.kanban.board.model.taskcard.TaskCardData
import woowacourse.kanban.board.model.taskcard.TaskStatus

data class Project(
    val title: String,
    val initialTasks: MutableList<TaskCardData>
) {
    private val tasks = mutableStateListOf<TaskCardData>().apply {
        addAll(initialTasks)
    }

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

    fun updateTaskStatus(task: TaskCardData, targetStatus: TaskStatus) {
        val idx = tasks.indexOfFirst { it == task }
        if (idx == -1) return

        tasks[idx] = tasks[idx].copy(task = targetStatus)
    }
}