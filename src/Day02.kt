fun main() {
    fun part1(input: List<String>): Any {
        var sum = 0
        input.forEach { lines ->
            val line = lines.split(" ").map {
                it.toInt()
            }
            if (checkLine(line))
                sum++
        }
        return sum
    }

    fun part2(input: List<String>): Any {
        var sum = 0
        input.forEach { lines ->
            val line = lines.split(" ").map {
                it.toInt()
            }
            if (checkLine2(line))
                sum++
        }

        return sum
    }


    val input = readInput("Day02")
    // val input = listOf(
    //     "7 6 4 2 8",
    //     "1 2 7 8 9",
    //     "9 7 6 2 1",
    //     "1 3 2 4 5",
    //     "8 6 4 4 1",
    //     "1 3 6 7 9"
    //)
    part1(input).println()
    part2(input).println()
}

fun checkLine(line: List<Int>): Boolean {

    var direction = true
    for (i in 1..<line.size) {
        if (i == 1 && line[0] > line[1]) {
            direction = false
        }
        if (direction) {
            if (line[i - 1] >= line[i] || (line[i] - line[i - 1]) >= 4) {
                return false
            }
        } else {
            if (line[i - 1] <= line[i] || (line[i - 1] - line[i]) >= 4) {
                return false
            }
        }
    }
    return true
}

fun checkLine2(line: List<Int>): Boolean {

    if (checkLine(line))
        return true
    if (checkLine(line.slice(1..<line.size)))
        return true
    for (i in 1..<line.size) {
        if (checkLine(line.slice((0..<i) + (i + 1..<line.size))))
            return true
    }

    return false
}