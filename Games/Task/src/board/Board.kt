package board

interface ICell {
    val i: Int
    val j: Int

    operator fun component1(): Int
    operator fun component2(): Int
}

enum class Direction {
    UP, DOWN, RIGHT, LEFT;

    fun reversed() = when (this) {
        UP -> DOWN
        DOWN -> UP
        RIGHT -> LEFT
        LEFT -> RIGHT
    }
}

interface SquareBoard {
    val width: Int

    fun getCellOrNull(i: Int, j: Int): ICell?
    fun getCell(i: Int, j: Int): ICell

    fun getAllCells(): Collection<ICell>

    fun getRow(i: Int, jRange: IntProgression): List<ICell>
    fun getColumn(iRange: IntProgression, j: Int): List<ICell>

    fun ICell.getNeighbour(direction: Direction): ICell?
}

interface GameBoard<T> : SquareBoard {

    operator fun get(cell: ICell): T?
    operator fun set(cell: ICell, value: T?)

    fun filter(predicate: (T?) -> Boolean): Collection<ICell>
    fun find(predicate: (T?) -> Boolean): ICell?
    fun any(predicate: (T?) -> Boolean): Boolean
    fun all(predicate: (T?) -> Boolean): Boolean
}