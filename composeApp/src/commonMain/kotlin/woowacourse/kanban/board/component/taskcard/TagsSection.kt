package woowacourse.kanban.board.component.taskcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.collections.immutable.toImmutableList
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Gray80
import woowacourse.kanban.board.model.taskcard.TaskTag
import woowacourse.kanban.board.model.taskcard.TaskTags

@Composable
fun TagsSection(
    taskTags: TaskTags,
    modifier: Modifier = Modifier,
) {
    if (taskTags.value.isNotEmpty()) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = modifier
                .fillMaxWidth(),
        ) {
            taskTags.value.forEach { tag ->
                TagBox(tag)
            }
        }
    }
}

@Composable
private fun TagBox(taskTag: TaskTag) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(14.dp))
            .background(
                color = Gray80,
            )
            .padding(vertical = 4.dp, horizontal = 6.dp),
    ) {
        Text(
            text = taskTag.value,
            fontSize = 12.sp,
            color = Gray20,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TagsSectionPreview() {
    val taskTags = TaskTags(value = listOf(TaskTag(value = "컴포넌트")).toImmutableList())
    TagsSection(taskTags = taskTags)
}
