fun main() {
    fun part1(input: List<String>): Any {
        var sum = 0L
        input[0].split(" ").forEach {
            sum += count(it.toLong(), 25, mutableMapOf())
        }
        return sum
        //Naiv solution
        /*        var oldArr = mutableListOf<Long>()
            var newArr = mutableListOf<Long>()
            input[0].split(" ").forEach {
                oldArr.add(it.toLong())
            }

            var i = 0
            while (i < 25) {
                 newArr = mutableListOf()
                for (ii in 0 until oldArr.size) {
                    if (oldArr[ii] == 0L) {
                        newArr.add(1L)

                    } else if (oldArr[ii].toString().length % 2 == 0) {
                        newArr.add(oldArr[ii].toString().substring(0, oldArr[ii].toString().length / 2).toLong())
                        newArr.add(oldArr[ii].toString().substring(oldArr[ii].toString().length / 2).toLong())

                    } else {
                        newArr.add(oldArr[ii] * 2024L)
                    }
                }
                oldArr = newArr
                i++
            }
            return newArr.size*/
    }


    fun part2(input: List<String>): Any {
        var sum = 0L
        input[0].split(" ").forEach {
            sum += count(it.toLong(), 75, mutableMapOf())
        }
        return sum
    }


    val input = readInput("Day11")
    //val input = listOf(
    //    "125 17"
    // )
    part1(input).println()
    part2(input).println()
}

fun count(stone: Long, steps: Int, cache: MutableMap<Pair<Long, Int>, Long>): Long {
    if (steps == 0) {
        return 1
    }
    if (stone == 0L) {
        return cache.getOrPut(Pair(1L, steps - 1)) { count(1, steps - 1, cache) }
    }

    if (stone.toString().length % 2 == 0) {
        val leftStone = stone.toString().substring(0, stone.toString().length / 2).toLong()
        val rightStone = stone.toString().substring(stone.toString().length / 2).toLong()
        return cache.getOrPut(Pair(leftStone, steps - 1)) { count(leftStone, steps - 1, cache) } + cache.getOrPut(
            Pair(rightStone, steps - 1)
        ) { count(rightStone, steps - 1, cache) }
    }
    return cache.getOrPut(Pair(stone * 2024L, steps - 1)) { count(stone * 2024, steps - 1, cache) }

}
