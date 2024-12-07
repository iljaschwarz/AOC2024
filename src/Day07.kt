import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Any {
        var sum = 0L
        input.forEach { s ->
            val line = s.split(": ")
            val total = line[0].toLong()
            val numbers = line[1].split(" ").map { it.toLong() }
            val possibleVariations = (2.0).pow(numbers.size - 1).toInt()
            for (i in 0..<possibleVariations) {
                var temp = numbers[0]
                val binary = Integer.toBinaryString(i).padStart(numbers.size - 1, '0')
                for (j in binary.indices) {
                    if (binary[j] == '1')
                        temp += numbers[j + 1]
                    else
                        temp *= numbers[j + 1]
                }
                if (temp == total) {
                    sum += total
                    break
                }
            }
        }

        return sum
    }

    fun part2(input: List<String>): Any {
        var sum = 0L
        input.forEach { s ->
            val line = s.split(": ")
            val total = line[0].toLong()
            val numbers = line[1].split(" ").map { it.toLong() }
            val possibleVariations = (3.0).pow(numbers.size - 1).toInt()
            for (i in 0..<possibleVariations) {
                var temp = numbers[0]
                val trinary = i.toString(3).padStart(numbers.size - 1, '0')
                for (j in trinary.indices) {
                    if (trinary[j] == '1')
                        temp += numbers[j + 1]
                    else if (trinary[j] == '2')
                        temp = (temp.toString() + numbers[j + 1].toString()).toLong()
                    else
                        temp *= numbers[j + 1]
                }
                if (temp == total) {
                    sum += total
                    break
                }
            }
        }
        return sum
    }

    val input = readInput("Day07")
    /*val input = listOf(
        "190: 10 19",
        "3267: 81 40 27",
        "83: 17 5",
        "156: 15 6",
        "7290: 6 8 6 15",
        "161011: 16 10 13",
        "192: 17 8 14",
        "21037: 9 7 18 13",
        "292: 11 6 16 20"
    )*/
    part1(input).println()
    part2(input).println()
}