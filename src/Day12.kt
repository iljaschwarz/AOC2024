fun main() {
    fun inBound(x: Int, y: Int, height: Int, width: Int): Boolean {
        return x in 0..<height && y in 0..<width
    }

    fun part1(input: List<String>): Any {
        var sum = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (row in input.indices) {
            for (col in input[row].indices) {
                if (Pair(row, col) in visited) {
                    continue
                }
                val stack = ArrayDeque<Pair<Int, Int>>()
                stack.add(Pair(row, col))
                var area = 0
                var permiter = 0
                while (stack.isNotEmpty()) {
                    val (x, y) = stack.removeFirst()
                    if (Pair(x, y) in visited) {
                        continue
                    }
                    visited.add(Pair(x, y))
                    area++
                    for (dir in Direction.entries) {
                        val (dx, dy) = when (dir) {
                            Direction.UP -> Pair(-1, 0)
                            Direction.DOWN -> Pair(1, 0)
                            Direction.LEFT -> Pair(0, -1)
                            Direction.RIGHT -> Pair(0, 1)
                        }
                        val newX = x + dx
                        val newY = y + dy
                        if (inBound(newX, newY, input.size, input[0].length) && input[newX][newY] == input[x][y]) {
                            stack.addLast(Pair(newX, newY))
                        } else {
                            permiter++
                        }
                    }
                }
                sum += area * permiter
            }
        }
        return sum
    }

    fun part2(input: List<String>): Any {

        var sum = 0
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (row in input.indices) {
            for (colum in input[row].indices) {
                if (Pair(row, colum) in visited) {
                    continue
                }
                val stack = ArrayDeque<Pair<Int, Int>>()
                stack.add(Pair(row, colum))
                var area = 0
                var perim = 0
                val perimMap = mutableMapOf<Pair<Int, Int>, MutableList<Pair<Int, Int>>>()
                while (stack.isNotEmpty()) {
                    val (x, y) = stack.removeFirst()
                    if (Pair(x, y) in visited) {
                        continue
                    }
                    visited.add(Pair(x, y))
                    area++
                    for (dir in Direction.entries) {
                        val (dx, dy) = when (dir) {
                            Direction.UP -> Pair(-1, 0)
                            Direction.DOWN -> Pair(1, 0)
                            Direction.LEFT -> Pair(0, -1)
                            Direction.RIGHT -> Pair(0, 1)
                        }
                        val newX = x + dx
                        val newY = y + dy
                        if (inBound(newX, newY, input.size, input[0].length) && input[newX][newY] == input[x][y]) {
                            stack.add(Pair(newX, newY))
                        } else {
                            perim++
                            if (Pair(dx, dy) !in perimMap) {
                                perimMap[Pair(dx, dy)] = mutableListOf()
                            }
                            perimMap[Pair(dx, dy)]!!.add(Pair(x, y))
                        }
                    }
                }
                var sides = 0
                for ((_, vs) in perimMap) {
                    val seenPerim = mutableSetOf<Pair<Int, Int>>()
                    for ((x, y) in vs) {
                        if (Pair(x, y) !in seenPerim) {
                            sides++
                            val stack2 = ArrayDeque<Pair<Int, Int>>()
                            stack2.add(Pair(x, y))
                            while (stack2.isNotEmpty()) {
                                val (curX, curY) = stack2.removeFirst()
                                if (Pair(curX, curY) in seenPerim) {
                                    continue
                                }
                                seenPerim.add(Pair(curX, curY))
                                for (dir in Direction.entries) {
                                    val (dx, dy) = when (dir) {
                                        Direction.UP -> Pair(-1, 0)
                                        Direction.DOWN -> Pair(1, 0)
                                        Direction.LEFT -> Pair(0, -1)
                                        Direction.RIGHT -> Pair(0, 1)
                                    }
                                    val newX = curX + dx
                                    val newY = curY + dy
                                    if (Pair(newX, newY) in vs) {
                                        stack2.add(Pair(newX, newY))
                                    }
                                }
                            }
                        }
                    }
                }
                sum += area * sides
            }
        }
        return sum
    }


    val input = readInput("Day12")
    /*val input = listOf(
        "AAAA",
        "BBCD",
        "BBCC",
        "EEEC"
    )
    */
    part1(input).println()
    part2(input).println()
}
