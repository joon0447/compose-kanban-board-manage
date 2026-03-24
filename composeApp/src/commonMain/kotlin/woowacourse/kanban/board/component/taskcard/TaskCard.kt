package woowacourse.kanban.board.component.taskcard

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import woowacourse.kanban.board.Gray70
import woowacourse.kanban.board.Gray80
import woowacourse.kanban.board.model.TaskState
import woowacourse.kanban.board.model.modal.Description
import woowacourse.kanban.board.model.modal.ProfileState
import woowacourse.kanban.board.model.modal.Tag
import woowacourse.kanban.board.model.modal.Tags
import woowacourse.kanban.board.model.modal.Title
import woowacourse.kanban.board.model.taskcard.TaskCardData

@Composable
fun TaskCard(
    data: TaskCardData,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(286.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, Gray70),
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = modifier
                .padding(17.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Title(title = data.title.value)
            Description(description = data.description.value)
            Tags(tags = data.tags)
            HorizontalDivider(
                thickness = 1.dp,
                color = Gray80,
            )
            Profile(profile = data.profile)
        }
    }
}

@Preview
@Composable
private fun TaskCardPreview() {
    TaskCard(
        data = TaskCardData(
            title = Title(value = "LazyColumn 컴포넌트 구현"),
            description = Description(value = "세로 스크롤 가능한 리스트 컴포넌트를 만들고 성능 최적화를 적용합니다."),
            tags = Tags(value = listOf(Tag(value = "컴포넌트"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
}

@Preview
@Composable
private fun TaskCardEmptyDescriptionPreview() {
    TaskCard(
        data = TaskCardData(
            title = Title(value = "LazyColumn 컴포넌트 구현"),
            description = Description(value = ""),
            tags = Tags(value = listOf(Tag(value = "컴포넌트"))),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
}

@Preview
@Composable
private fun TaskCardEmptyTagPreview() {
    TaskCard(
        data = TaskCardData(
            title = Title(value = "LazyColumn 컴포넌트 구현"),
            description = Description(value = "세로 스크롤"),
            tags = Tags(value = emptyList()),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
}

@Preview
@Composable
private fun TaskCardEmptyTagAndDescriptionPreview() {
    TaskCard(
        data = TaskCardData(
            title = Title(value = "LazyColumn 컴포넌트 구현"),
            description = Description(value = ""),
            tags = Tags(value = emptyList()),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
}

@Preview
@Composable
private fun TaskCardLongTitlePreview() {
    TaskCard(
        data = TaskCardData(
            title = Title(value = "LazyColumn 컴포넌트 구현LazyColumn 컴포넌트 구현"),
            description = Description(value = ""),
            tags = Tags(value = emptyList()),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
}

@Preview
@Composable
private fun TaskCardLongDescriptionPreview() {
    TaskCard(
        data = TaskCardData(
            title = Title(value = "LazyColumn 컴포넌트 구현"),
            description = Description(
                value = "세로 스크롤  세로 스크롤" +
                    "세로 스크롤 세로 스크롤세로 스크롤 " +
                    " 세로 스크롤세로 스크롤 세로 스크롤" +
                    "세로 스크롤  세로 스크롤세로 스크롤 " +
                    "세로 스크롤세로 스크롤  세로 스크롤" +
                    "세로 스크롤 세로 스크롤세로 스크롤" +
                    "  세로 스크롤세로 스크롤 세로 스크롤"
            ),
            tags = Tags(value = emptyList()),
            task = TaskState.PROGRESS,
            profile = ProfileState.DINO,
        ),
    )
}
