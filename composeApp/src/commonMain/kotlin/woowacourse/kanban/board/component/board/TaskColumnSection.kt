package woowacourse.kanban.board.component.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.component.extension.toBackgroundColor
import woowacourse.kanban.board.component.extension.toBorderColor
import woowacourse.kanban.board.component.extension.toHeaderColor
import woowacourse.kanban.board.component.extension.toText
import woowacourse.kanban.board.component.taskcard.TaskCard
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.modal.Description
import woowacourse.kanban.board.model.modal.ProfileState
import woowacourse.kanban.board.model.modal.Tag
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun TaskColumnSection(
    todoTasks: List<TaskCardData>,
    progressTasks: List<TaskCardData>,
    doneTasks: List<TaskCardData>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TaskColumn(
            taskState = TaskState.TODO,
            tasks = todoTasks,
            modifier = Modifier.weight(1f),
        )
        TaskColumn(
            taskState = TaskState.PROGRESS,
            tasks = progressTasks,
            modifier = Modifier.weight(1f),
        )
        TaskColumn(
            taskState = TaskState.DONE,
            tasks = doneTasks,
            modifier = Modifier.weight(1f),
        )
        Spacer(
            modifier = Modifier.weight(.7f)
        )
    }
}
@Composable
private fun TaskColumn(
    taskState: TaskState,
    tasks: List<TaskCardData>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .border(1.dp, taskState.toBorderColor(), shape = RoundedCornerShape(15.dp))
            .background(taskState.toBackgroundColor()),
    ) {
        Column {
            TaskColumnHeader(
                taskState = taskState,
                taskCount = tasks.size,
                modifier = Modifier.fillMaxWidth(),
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(tasks) { task ->
                    TaskCard(
                        data = task,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}

@Composable
private fun TaskColumnHeader(
    taskState: TaskState,
    taskCount: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(taskState.toHeaderColor())
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = taskState.toText(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .background(Color.White),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = taskCount.toString(),
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                fontWeight = FontWeight.Normal,
            )
        }
    }
}

@Preview(widthDp = 600)
@Composable
private fun TaskColumnProgressHeaderPreview() {
    TaskColumnHeader(
        taskState = TaskState.PROGRESS,
        taskCount = 1,
    )
}

@Preview(widthDp = 600)
@Composable
private fun TaskColumnDoneHeaderPreview() {
    TaskColumnHeader(
        taskState = TaskState.DONE,
        taskCount = 1,
    )
}

@Preview(widthDp = 600)
@Composable
private fun TaskColumnTodoHeaderPreview() {
    TaskColumnHeader(
        taskState = TaskState.TODO,
        taskCount = 1,
    )
}

@Preview(heightDp = 400)
@Composable
private fun TaskColumnTodoPreview() {
    val tasks = listOf(
        TaskCardData(
            title = Title(value = "제목"),
            description = Description(value = "설명"),
            tags = Tags(value = listOf(Tag(value = "컴포넌트"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
    TaskColumn(
        tasks = tasks,
        taskState = TaskState.TODO,
    )
}

@Preview(heightDp = 400)
@Composable
private fun TaskColumnProgressPreview() {
    val tasks = listOf(
        TaskCardData(
            title = Title(value = "제목"),
            description = Description(value = "설명"),
            tags = Tags(value = listOf(Tag(value = "컴포넌트"), Tag("zjavh"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
    TaskColumn(
        tasks = tasks,
        taskState = TaskState.PROGRESS,
    )
}

@Preview(heightDp = 400)
@Composable
private fun TaskColumnDonePreview() {
    val tasks = listOf(
        TaskCardData(
            title = Title(value = "제목"),
            description = Description(value = "설명"),
            tags = Tags(value = listOf(Tag(value = "컴포넌트"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
    TaskColumn(
        tasks = tasks,
        taskState = TaskState.DONE,
    )
}
