package woowacourse.kanban.board.component.modal

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.isEditable
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.runComposeUiTest
import kanbanboard.composeapp.generated.resources.Res
import kanbanboard.composeapp.generated.resources.profile
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.component.workspace.ModalState
import woowacourse.kanban.board.model.modal.ModalType
import woowacourse.kanban.board.model.taskcard.Assignee
import kotlin.test.Test

@OptIn(ExperimentalTestApi::class)
class ModalTest {

    private lateinit var assignees: ImmutableList<Assignee>

    @Before
    fun setUp() {
        assignees = listOf(
            Assignee("다이노", Res.drawable.profile),
            Assignee("페임스", Res.drawable.profile)
        ).toImmutableList()
    }

    @Test
    fun `초기 상태에서 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = {},
                modalType = ModalType.Create({}),
                modalState = ModalState(assignees)
            )
        }
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목을 입력하면 생성 버튼이 활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = {},
                modalType = ModalType.Create({}),
                modalState = ModalState(assignees)
            )
        }
        onNodeWithText(ComponentText.TITLE_PLACEHOLDER).performTextInput("하이")
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsEnabled()
    }

    @Test
    fun `제목을 입력하고 태그에 ,,을 연속으로 입력하면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = {},
                modalType = ModalType.Create({}),
                modalState = ModalState(assignees)
            )
        }
        onNodeWithText(ComponentText.TITLE_PLACEHOLDER).performTextInput("하이")
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsEnabled()
        onNodeWithText(ComponentText.TAG_PLACEHOLDER).performTextInput("태그,,태그2")
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `제목을 입력한 뒤 모두 지우면 생성 버튼이 비활성화된다`() = runComposeUiTest {
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = {},
                modalType = ModalType.Create({}),
                modalState = ModalState(assignees)
            )
        }
        onAllNodes(isEditable())[0].performTextInput("하이")
        onAllNodes(isEditable())[0].performTextClearance()
        waitForIdle()
        onNodeWithText(ComponentText.CREATE_BUTTON).assertIsNotEnabled()
    }

    @Test
    fun `Modal 헤더의 닫기 버튼을 누르면 onClickClose가 호출된다`() = runComposeUiTest {
        var close = false
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = { close = true },
                modalType = ModalType.Create({}),
                modalState = ModalState(assignees)
            )
        }
        onNodeWithContentDescription("닫기").performClick()
        assertThat(close).isTrue()
    }

    @Test
    fun `취소 버튼을 누르면 onClickClose가 호출된다`() = runComposeUiTest {
        var close = false
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = { close = true },
                modalType = ModalType.Create({}),
                modalState = ModalState(assignees)
            )
        }
        onNodeWithText(ComponentText.CANCEL_BUTTON).performSemanticsAction(SemanticsActions.OnClick)
        assertThat(close).isTrue()
    }

    @Test
    fun `제목을 입력하고 생성 버튼을 누르면 onClickTaskCreate가 호출된다`() = runComposeUiTest {
        var create = false
        setContent {
            Modal(
                assignees = assignees,
                onClickClose = { },
                modalType = ModalType.Create({
                    create = true
                }),
                modalState = ModalState(assignees)
            )
        }
        onAllNodes(isEditable())[0].performTextInput("하이")
        onNodeWithText(ComponentText.CREATE_BUTTON).performSemanticsAction(SemanticsActions.OnClick)
        assertThat(create).isTrue()
    }
}
