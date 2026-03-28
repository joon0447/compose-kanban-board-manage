package woowacourse.kanban.board.component.board

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

class BoardState {

    val snackbarHostState = SnackbarHostState()
    var shouldShowSnackbar by mutableStateOf(false)
    var shouldShowMoveSnackbar by mutableStateOf(false)
    var isShowModal by mutableStateOf(false)

    fun showSnackBar() {
        shouldShowSnackbar = true
    }

    fun closeModal() {
        isShowModal = false
    }
}

@Composable
fun rememberBoardState(): BoardState = remember { BoardState() }