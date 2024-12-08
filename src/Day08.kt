fun main() {
    fun part1(input: List<String>): Any {
        val height = input.size
        val width = input[0].length
        val antennas = parseInput(input)
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (antenna in antennas) {
            for (first in antenna.value.indices) {
                for (second in first + 1 until antenna.value.size) {

                    val (ax, ay) = antenna.value[first]
                    val (bx, by) = antenna.value[second]
                    val cx = ax - (bx - ax)
                    val cy = ay - (by - ay)
                    val dx = bx + (bx - ax)
                    val dy = by + (by - ay)
                    if (inBound(cx, cy, height, width)) {
                        visited.add(Pair(cx, cy))
                    }
                    if (inBound(dx, dy, height, width)) {
                        visited.add(Pair(dx, dy))
                    }

                }
            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Any {
        val height = input.size
        val width = input[0].length

        val antennas = parseInput(input)
        val visited = mutableSetOf<Pair<Int, Int>>()
        for (antenna in antennas) {
            for (first in antenna.value.indices) {
                for (second in first + 1 until antenna.value.size) {

                    val (ax, ay) = antenna.value[first]
                    val (bx, by) = antenna.value[second]
                    val dx = bx - ax
                    val dy = by - ay
                    var i = 0
                    while (true) {
                        if (inBound(ax - dx * i, ay - dy * i, height, width)) {
                            visited.add(Pair(ax - dx * i, ay - dy * i))
                        } else {
                            break
                        }
                        i++
                    }
                    i = 0
                    while (true) {
                        if (inBound(bx + dx * i, by + dy * i, height, width)) {
                            visited.add(Pair(bx + dx * i, by + dy * i))
                        } else {
                            break
                        }
                        i++
                    }
                }
            }

        }
        return visited.size
    }

    val input = readInput("Day08")
    /*val input = listOf(
        "............",
        "........0...",
        ".....0......",
        ".......0....",
        "....0.......",
        "......A.....",
        "............",
        "............",
        "........A...",
        ".........A..",
        "............",
        "............"
        )
    */

    part1(input).println()
    part2(input).println()
}

fun inBound(a: Int, b: Int, c: Int, d: Int): Boolean {
    return a in 0..<c && b in 0..<d

}


fun parseInput(input: List<String>): MutableMap<Char, ArrayList<Pair<Int, Int>>> {
    val antennas = mutableMapOf<Char, ArrayList<Pair<Int, Int>>>()
    for (row in input.indices) {
        for (col in input[row].indices) {
            if (input[row][col] != '.') {
                antennas[input[row][col]] = antennas.getOrDefault(input[row][col], arrayListOf())
                antennas[input[row][col]]!!.add(Pair(row, col))
            }
        }
    }
    return antennas
}