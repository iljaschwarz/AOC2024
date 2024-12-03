fun main() {
    fun part1(input: List<String>): Any {
        var sum = 0

        val reg = Regex("mul\\((\\d+),(\\d+)\\)")
        for (i in input.indices) {
            reg.findAll(input[i]).forEach {
                val (a, b) = it.destructured
                sum += a.toInt() * b.toInt()
            }
        }
        return sum
    }

    fun part2(input: List<String>): Any {
        var sum = 0
        var shouldAdd = true
        val reg = Regex("mul\\((\\d+),(\\d+)\\)|(do\\(\\)|don't\\(\\))")
        for (i in input.indices) {
            reg.findAll(input[i]).forEach {
                when (it.value) {
                    "do()" -> shouldAdd = true
                    "don't()" -> shouldAdd = false
                    else -> {
                        val (a, b) = it.destructured
                        if (shouldAdd) {
                            sum += a.toInt() * b.toInt()
                        }
                    }
                }
            }
        }
        return sum
    }


    val input = readInput("Day03")
    //  val input = listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")
    part1(input).println()
    part2(input).println()
}
