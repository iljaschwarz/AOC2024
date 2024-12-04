fun main() {
    fun part1(input: List<String>): Any {
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == 'X') {
                    sum += checkNeighbours(input, i, j)
                }
            }
        }
        return sum
    }

    fun part2(input: List<String>): Any {
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == 'A') {
                    sum += checkNeighbours2(input, i, j)
                }
            }
        }
        return sum
    }


    val input = readInput("Day04")
//    val input = listOf(
//        "MMMSXXMASM",
//        "MSAMXMSMSA",
//        "AMXSXMAAMM",
//        "MSAMASMSMX",
//        "XMASAMXAMM",
//        "XXAMMXXAMA",
//        "SMSMSASXSS",
//        "SAXAMASAAA",
//        "MAMMMXMMMM",
//        "MXMXAXMASX"
//
//    )
    part1(input).println()
    part2(input).println()
}

fun checkNeighbours(input: List<String>, i: Int, j: Int): Int {
    var sum = 0
    //left
    if (j > 2 && input[i][j - 1] == 'M' && input[i][j - 2] == 'A' && input[i][j - 3] == 'S') {
        sum++
    }
    //right
    if (j < input[0].length - 3 && input[i][j + 1] == 'M' && input[i][j + 2] == 'A' && input[i][j + 3] == 'S') {
        sum++
    }
    //up
    if (i > 2 && input[i - 1][j] == 'M' && input[i - 2][j] == 'A' && input[i - 3][j] == 'S') {
        sum++
    }
    //down
    if (i < input.size - 3 && input[i + 1][j] == 'M' && input[i + 2][j] == 'A' && input[i + 3][j] == 'S') {
        sum++
    }
    //left-up
    if (i > 2 && j > 2 && input[i - 1][j - 1] == 'M' && input[i - 2][j - 2] == 'A' && input[i - 3][j - 3] == 'S') {
        sum++
    }
    //right-up
    if (i > 2 && j < input[0].length - 3 && input[i - 1][j + 1] == 'M' && input[i - 2][j + 2] == 'A' && input[i - 3][j + 3] == 'S') {
        sum++
    }
    //left-down
    if (i < input.size - 3 && j > 2 && input[i + 1][j - 1] == 'M' && input[i + 2][j - 2] == 'A' && input[i + 3][j - 3] == 'S') {
        sum++
    }
    //right-down
    if (i < input.size - 3 && j < input[0].length - 3 && input[i + 1][j + 1] == 'M' && input[i + 2][j + 2] == 'A' && input[i + 3][j + 3] == 'S') {
        sum++
    }
    return sum
}

fun checkNeighbours2(input: List<String>, i: Int, j: Int): Int {
    var sum = 0
    // M Left-up M Right-up
    if (i > 0 && j > 0 && j < input[0].length - 1 && i < input[0].length - 1  &&
        input[i - 1][j - 1] == 'M' &&
        input[i + 1][j - 1] == 'M' &&
        input[i + 1][j + 1] == 'S' &&
        input[i - 1][j + 1] == 'S'
    ) {
        sum++
    }
    // M Left-up S Right-up
    if (i > 0 && j > 0 && j < input[0].length - 1 && i < input[0].length - 1 &&
        input[i - 1][j - 1] == 'M' &&
        input[i + 1][j - 1] == 'S' &&
        input[i + 1][j + 1] == 'S' &&
        input[i - 1][j + 1] == 'M'
    ) {
        sum++
    }
    // S Left-up S Right-up
    if (i > 0 && j > 0 && j < input[0].length - 1 && i< input[0].length - 1 &&
        input[i - 1][j - 1] == 'S' &&
        input[i + 1][j - 1] == 'S' &&
        input[i + 1][j + 1] == 'M' &&
        input[i - 1][j + 1] == 'M'
    ) {
        sum++
    }
    // S Left-up M Right-up
    if (i > 0 && j > 0 && j < input[0].length - 1 && i < input[0].length - 1  &&
        input[i - 1][j - 1] == 'S' &&
        input[i + 1][j - 1] == 'M' &&
        input[i + 1][j + 1] == 'M' &&
        input[i - 1][j + 1] == 'S'
    ) {
        sum++
    }
    return sum
}
