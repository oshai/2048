# 2048

## Instructions

Coursera: https://www.coursera.org/learn/kotlin-for-java-developers/supplement/6HrU1/setting-up-programming-assignments  
Jetbrains: https://www.jetbrains.com/help/education/coursera-programming-assignments.html  
Edu-tools: https://plugins.jetbrains.com/plugin/10081-edutools

## Board

Your task is to implement interfaces SquareBoard and GameBoard.

### SquareBoard

SquareBoard stores the information about the square board and all the cells on it. It allows to get a cell by its indexes, get parts of columns and rows on a board, or get a specified neighbour of a cell.

Note that the numbering of cells starts with 1, not with 0. A board of a width 2 consists of the following cells:

(1, 1) (1, 2)
(2, 1) (2, 2)
For the following examples, we'll use this board of a width 2:

val board = createSquareBoard(2)
If you call board.getCellOrNull(3, 3) for such board, you'll get null as the result, because the board doesn't have a cell with such coordinates. The function Board.getCell should throw IllegalArgumentException for incorrect values of i and j.

You can write board.getRow(1, 1..2) or board.getRow(1, 2 downTo 1), and you'll get the lists of cells [(1, 1), (1, 2)] and [(1, 2), (1, 1)] accordingly. Note how using the range 2 downTo 1 returns a row in a reversed order. You can use any range to get a part of a column or a row.

Note that getRow and getColumn should return a list containing only the cells that belong to the board if the range is larger than the board limits and ignore other indexes, thus, board.getRow(1, 1..10) should return [(1, 1), (1, 2)].

The neighbours of a cell (1, 1) depending on the direction should be:

Direction.UP - null     
Direction.LEFT - null     
Direction.DOWN - (2, 1) 
Direction.RIGHT - (1, 2)
Create only width * width cells; all the functions working with cells should return existing cells instead of creating new ones.

### GameBoard

GameBoard lets to store the values in board cells, update them, and ask the general information about the stored values (like any, all etc.) Note that GameBoard extends SquareBoard.

See TestSquareBoard and TestGameBoard for examples.

## Game

Your task is to implement two games: [Game 2048](https://en.wikipedia.org/wiki/2048_\(video_game\)) and [Game of Fifteen](https://en.wikipedia.org/wiki/15_puzzle). Use your implementation of the GameBoard interface from the previous task.

After implementing the game you can play it yourself running main function in ui/PlayGame2048 or ui/PlayGameOfFifteen.

### Game 2048

First, complete the tasks in Game2048Helper.kt (implementing the function moveAndMergeEqual declared in Game2048Helper.kt) and in Game2048Initializer.kt (generating new values randomly). Then, implement the utility functions declared in Game2048.kt. The tests which you can run to check each function are specified in the comments next to the function.

### Game of Fifteen

Game of Fifteen is solvable only if the initial permutation of numbers is [even](https://en.wikipedia.org/wiki/Parity_of_a_permutation). Implement first the function isEven (declared in GameOfFifteenHelper.kt) checking whether a permutation is even or odd, and then use this function to produce only solvable permutations in GameOfFifteenIntiializer.kt.

You can use the following algorithm to check the given permutation. Let P is a permutation function on a range of numbers 1..n. For a pair (i, j) of elements such that i < j , if P(i) > P(j), then the permutation is said to invert the order of (i, j). The number of such inverted pairs is the parity of the permutation. If permutation inverts even number of such pairs it is an even permutation else it is an odd permutation.
