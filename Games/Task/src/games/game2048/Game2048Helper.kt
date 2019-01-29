package games.game2048

/*
 * This function moves all the non-null elements to the beginning of the list (by removing nulls) and merges equal elements.
 * The parameter 'double' specifies the way how to merge equal elements:
 * it returns a new element that should be present in the result list instead of two merged elements.
 *
 * If the function double("a") returns "aa",
 * then the function moveAndMergeEqual transforms the input in the following way:
 *   a, a, b -> aa, b
 *   a, null -> a
 *   b, null, a, a -> b, aa
 *   a, a, null, a -> aa, a
 *   a, null, a, a -> aa, a
*/

enum class FoldingState {
    DidNotMergeYet,
    MappedTheFirstValue,
    DeletedTheSecondValue
}

fun <T : Any> List<T?>.moveAndMergeEqual(double: (T) -> T): List<T> {
    val notNullList = this.filterNotNull()
    val mergedList =
            notNullList.drop(1).mapIndexedNotNull { index, value ->
                value.takeIf { notNullList[index] == value }
            }.firstOrNull()
                    ?.let { firstDuplicatedValue ->
                        notNullList.fold(FoldingState.DidNotMergeYet to emptyList<T>()) { (foldingState, mappedList), currentValue ->
                            if (currentValue == firstDuplicatedValue)
                                when (foldingState) {
                                    FoldingState.DidNotMergeYet ->
                                        FoldingState.MappedTheFirstValue to (mappedList + double(currentValue))
                                    FoldingState.MappedTheFirstValue ->
                                        FoldingState.DeletedTheSecondValue to mappedList
                                    FoldingState.DeletedTheSecondValue ->
                                        foldingState to (mappedList + currentValue)
                                }
                            else foldingState to (mappedList + currentValue)
                        }.second
                    }
    return mergedList?.moveAndMergeEqual(double) ?: notNullList
}
