package board

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)

data class Cell(override val i: Int, override val j: Int) : ICell

open class SquareBoardImpl(final override val width: Int) : SquareBoard {
    private val cells: List<List<Cell>>

    init {
        cells = List(width) { i ->
            List(width) { j ->
                Cell(i + 1, j + 1)
            }
        }
    }

    override fun getCellOrNull(i: Int, j: Int): ICell? =
            cells.getOrNull(i - 1)?.getOrNull(j - 1)

    override fun getCell(i: Int, j: Int): ICell =
            getCellOrNull(i, j) ?: throw IllegalArgumentException()

    override fun getAllCells(): Collection<ICell> =
            cells.flatten()

    override fun getRow(i: Int, jRange: IntProgression): List<ICell> =
            jRange.mapNotNull { j ->
                cells.getOrNull(i - 1)?.getOrNull(j - 1)
            }

    override fun getColumn(iRange: IntProgression, j: Int): List<ICell> =
            iRange.mapNotNull { i ->
                cells.getOrNull(i - 1)?.getOrNull(j - 1)
            }

    override fun ICell.getNeighbour(direction: Direction): ICell? =
            when (direction) {
                Direction.UP -> getCellOrNull(i - 1, j)
                Direction.DOWN -> getCellOrNull(i + 1, j)
                Direction.LEFT -> getCellOrNull(i, j - 1)
                Direction.RIGHT -> getCellOrNull(i, j + 1)
            }

}

class GameBoardImpl<T>(width: Int) : SquareBoardImpl(width), GameBoard<T> {
    private val cellsValues = mutableMapOf<ICell, T?>()

    override fun get(cell: ICell): T? =
            cellsValues[cell]

    override fun set(cell: ICell, value: T?) {
        cellsValues[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<ICell> =
            super.getAllCells().filter { predicate(get(it)) }


    override fun find(predicate: (T?) -> Boolean): ICell? =
            super.getAllCells().find { predicate(get(it)) }

    override fun any(predicate: (T?) -> Boolean): Boolean =
            super.getAllCells().any { predicate(get(it)) }

    override fun all(predicate: (T?) -> Boolean): Boolean =
            super.getAllCells().all { predicate(get(it)) }
}
