fun main() {
    fun part1(input: List<String>): Any {
        val left = ArrayList<Int>()
        val right = ArrayList<Int>()
        var sum = 0

        input.forEach {
            val line = it.split("   ")
            left.add(line[0].toInt())
            right.add(line[1].toInt())
        }
        left.sort()
        right.sort()
        for (i in 0..<left.size) {
            sum += if (left[i] > right[i])
                left[i] - right[i]
            else
                right[i] - left[i]
        }
        return sum
    }

    fun part2(input: List<String>): Any {
        var sum = 0
        val left = ArrayList<Int>()
        val right = HashMap<Int, Int>()
        input.forEach {
            val line = it.split("   ")
            left.add(line[0].toInt())
            val i = right.getOrElse(line[1].toInt()) { 0 }
            right[line[1].toInt()] = i + 1

        }
        left.forEach {
            if (right.contains(it)) {
                sum += it * right[it]!!
            }
        }

        return sum
    }


    val input = readInput("Day01")
    //val input = listOf("3   4" ,
    //         "4   3",
    //        "2   5" ,
    //         "1   3" ,
    //          "3   9" ,
    //          "3   3")
    part1(input).println()
    part2(input).println()
}