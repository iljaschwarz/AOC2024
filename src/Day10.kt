fun main() {
    fun inBound(x: Int, y: Int, height: Int, width: Int): Boolean {
        return x in 0..<height && y in 0..<width
    }

    fun score(input: List<String>, x: Int, y: Int): Int {
        if (input[x][y].toString().toInt() != 0) {
            return 0
        }
        var sum = 0
        val stack = ArrayDeque<Pair<Int, Int>>()

        stack.add(Pair(x, y))
        val visited = mutableSetOf<Pair<Int, Int>>()
        while (stack.isNotEmpty()) {
            val (curx, cury) = stack.removeLast()
            val cur = input[curx][cury].toString().toInt()

            if (Pair(curx, cury) in visited) {
                continue
            }
            visited.add(Pair(curx, cury))
            if (cur == 9) {
                sum += 1
                continue
            }
            for (dir in Directions.entries) {
                val (dx, dy) = when (dir) {
                    Directions.UP -> Pair(-1, 0)
                    Directions.DOWN -> Pair(1, 0)
                    Directions.LEFT -> Pair(0, -1)
                    Directions.RIGHT -> Pair(0, 1)

                }
                val newX = curx + dx
                val newY = cury + dy
                if (!inBound(newX, newY, input.size, input[0].length)) {

                    continue
                }
                val neighbor = input[newX][newY].toString().toInt()
                if (neighbor != (cur + 1)) {
                    continue
                }
                stack.addLast(Pair(newX, newY))
            }
        }
        return sum
    }


    fun rating(input: List<String>, x: Int, y: Int): Int {
        if (input[x][y].toString().toInt() == 9) {
            return 1
        }
        var sum = 0
        for (dir in Directions.entries) {
            val (dx, dy) = when (dir) {
                Directions.UP -> Pair(-1, 0)
                Directions.DOWN -> Pair(1, 0)
                Directions.LEFT -> Pair(0, -1)
                Directions.RIGHT -> Pair(0, 1)

            }
            val newX = x + dx
            val newY = y + dy
            if (!inBound(newX, newY, input.size, input[0].length)) {
                continue
            }
            if (input[newX][newY].toString().toInt() == (input[x][y].toString().toInt() + 1)) {
                sum += rating(input, newX, newY)
            }
        }
        return sum
    }

    fun part1(input: List<String>): Any {
        var sum = 0
        for (i in input.indices) {
            for (j in 0..<input[i].length) {
                sum += score(input, i, j)
            }
        }
        return sum
    }

    fun part2(input: List<String>): Any {
        var sum = 0
        for (i in input.indices) {
            for (j in 0..<input[i].length) {
                if (input[i][j].toString().toInt() == 0) {
                    sum += rating(input, i, j)
                }
            }
        }
        return sum
    }


     val input = readInput("Day10")
   /* val input = listOf(
        "89010123",
        "78121874",
        "87430965",
        "96549874",
        "45678903",
        "32019012",
        "01329801",
        "10456732"
    )
    */part1(input).println()
    part2(input).println()
}

enum class Directions {
    UP, DOWN, LEFT, RIGHT
}
