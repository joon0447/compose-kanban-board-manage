package woowacourse.kanban.board.component.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import woowacourse.kanban.board.Blue50
import woowacourse.kanban.board.Gray20
import woowacourse.kanban.board.Purple40
import woowacourse.kanban.board.Red70
import woowacourse.kanban.board.component.ComponentText
import woowacourse.kanban.board.model.modal.ModalType

@Composable
fun Footer(
    onClickClose: () -> Unit,
    isButtonEnabled: Boolean,
    modalType: ModalType,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        HorizontalDivider()
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            FooterButton(
                containerColor = Color.Transparent,
                contentColor = Gray20,
                text = ComponentText.CANCEL_BUTTON,
                onClick = onClickClose,
            )
            Spacer(modifier = Modifier.width(12.dp))
            when (modalType) {
                is ModalType.Create -> {
                    FooterButton(
                        enabled = isButtonEnabled,
                        containerColor = Blue50,
                        text = ComponentText.CREATE_BUTTON,
                        onClick = modalType.onCreate,
                    )
                }
                is ModalType.Edit -> {
                    EditModalFooterButtons(
                        isButtonEnabled = isButtonEnabled
                    )
                }
            }
        }
    }
}

@Composable
private fun FooterButton(
    containerColor: Color,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    contentColor: Color = Color.Unspecified,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(10.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        )
    }
}

@Composable
private fun EditModalFooterButtons(
    isButtonEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        FooterButton(
            enabled = true,
            containerColor = Red70,
            text = ComponentText.DELETE_BUTTON,
            onClick = {},
        )
        FooterButton(
            enabled = isButtonEnabled,
            containerColor = Purple40,
            text = ComponentText.EDIT_BUTTON,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateFooterPreview() {
    Footer(
        onClickClose = {},
        isButtonEnabled = true,
        modalType = ModalType.Create({})
    )
}

@Preview(showBackground = true)
@Composable
private fun EditFooterPreview() {
    Footer(
        onClickClose = {},
        isButtonEnabled = true,
        modalType = ModalType.Edit(
            onDelete = {},
            onUpdate = {},
        ),
    )
}
