package woowacourse.kanban.board

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import woowacourse.kanban.board.component.board.Board
import woowacourse.kanban.board.model.state.BoardState

@Composable
fun App() {
    val boardState = remember { BoardState() }
    Board(boardState = boardState)
}
