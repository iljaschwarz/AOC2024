fun main() {
    fun isOutSide(nextPos: Pair<Int, Int>, input: List<String>) =
        nextPos.first < 0 || nextPos.second < 0 || nextPos.first >= input.size || nextPos.second >= input[0].length

    fun part1(input: List<String>): Any {

        val visited = mutableSetOf<Pair<Int, Int>>()
        var posX = 0
        var posY = 0
        var direction = Direction.UP
        val obsticals = mutableSetOf<Pair<Int, Int>>()
        for (row in input.indices) {
            for (colm in input[row].indices) {
                if (input[row][colm] == '#') {
                    obsticals.add(Pair(row, colm))
                }
                if (input[row][colm] == '^') {
                    visited.add(Pair(row, colm))
                    posY = row
                    posX = colm
                }
            }
        }
        var finished = false
        while (!finished) {
            val nextPos = when (direction) {
                Direction.UP -> Pair(posY - 1, posX)
                Direction.DOWN -> Pair(posY + 1, posX)
                Direction.LEFT -> Pair(posY, posX - 1)
                Direction.RIGHT -> Pair(posY, posX + 1)
            }
            if (nextPos in obsticals) {
                direction = when (direction) {
                    Direction.UP -> Direction.RIGHT
                    Direction.DOWN -> Direction.LEFT
                    Direction.LEFT -> Direction.UP
                    Direction.RIGHT -> Direction.DOWN
                }
            } else if (isOutSide(nextPos, input)) {
                finished = true
            } else {
                visited.add(nextPos)
                posY = nextPos.first
                posX = nextPos.second
            }
        }
        return visited.size
    }

    fun part2(input: List<String>): Any {
        var sum = 0
        var orgPosx = 0
        var orgPosy = 0
        val origObsticals = mutableSetOf<Pair<Int, Int>>()
        for (row in input.indices) {
            for (colm in input[row].indices) {
                if (input[row][colm] == '#') {
                    origObsticals.add(Pair(row, colm))
                }
                if (input[row][colm] == '^') {
                    orgPosy = row
                    orgPosx = colm
                }
            }
        }
        for (i in input.indices) {
            for (j in input[i].indices) {
                val visited = mutableSetOf<Triple<Int, Int, Direction>>()
                var direction = Direction.UP
                var posX = orgPosx
                var posY = orgPosy
                visited.add(Triple(posY, posX, direction))
                val obsticals = origObsticals.toMutableSet()
                obsticals.add(Pair(i, j))
                var loop = false
                var finished = false
                while (!finished && !loop) {
                    val nextPos = when (direction) {
                        Direction.UP -> Pair(posY - 1, posX)
                        Direction.DOWN -> Pair(posY + 1, posX)
                        Direction.LEFT -> Pair(posY, posX - 1)
                        Direction.RIGHT -> Pair(posY, posX + 1)
                    }
                    if (nextPos in obsticals) {
                        direction = when (direction) {
                            Direction.UP -> Direction.RIGHT
                            Direction.DOWN -> Direction.LEFT
                            Direction.LEFT -> Direction.UP
                            Direction.RIGHT -> Direction.DOWN
                        }
                    } else if (isOutSide(nextPos, input)) {
                        finished = true
                    } else {
                        if (visited.contains(Triple(nextPos.first, nextPos.second, direction))) {
                            loop = true
                            finished = true
                        }
                        visited.add(Triple(nextPos.first, nextPos.second, direction))
                        posY = nextPos.first
                        posX = nextPos.second
                    }

                }
                if (loop) {
                    sum++
                }
            }
        }
        return sum
    }


    val input = readInput("Day06")
//    val input = listOf(
//        "....#.....",
//        ".........#",
//        "..........",
//        "..#.......",
//        ".......#..",
//        "..........",
//        ".#..^.....",
//        "........#.",
//        "#.........",
//        "......#..."
//    )
    part1(input).println()
    part2(input).println()
}

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}
