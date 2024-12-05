fun main() {
    fun part1(input: List<String>): Any {
        return doTheWork(input, true)
    }

    fun part2(input: List<String>): Any {
        return doTheWork(input, false)
    }

    val input = readInput("Day05")
//    val input = listOf(
//        "47|53",
//        "97|13",
//        "97|61",
//        "97|47",
//        "75|29",
//        "61|13",
//        "75|53",
//        "29|13",
//        "97|29",
//        "53|29",
//        "61|53",
//        "97|53",
//        "61|29",
//        "47|13",
//        "75|47",
//        "97|75",
//        "47|61",
//        "75|61",
//        "47|29",
//        "75|13",
//        "53|13",
//        "",
//        "75,47,61,53,29",
//        "97,61,53,29,13",
//        "75,29,13",
//        "75,97,47,61,53",
//        "61,13,29",
//        "97,13,75,29,47"
//    )
    part1(input).println()
    part2(input).println()
}

fun doTheWork(input: List<String>, isPartOne: Boolean): Int {
    var sum = 0
    var firstParse = true
    val rules = mutableMapOf<Int, ArrayList<Int>>()
    input.forEach { s ->
        if (s.isEmpty()) {
            firstParse = false
            return@forEach
        }
        if (firstParse) {
            val (a, b) = s.split("|")
            val rightSide = rules[a.toInt()] ?: ArrayList()
            rightSide.add(b.toInt())
            rules[a.toInt()] = rightSide
        } else {
            val numbers = s.split(",").map { it.toInt() }
            if (isPartOne) {
                sum += checkLine(numbers, rules)
            } else {
                sum += checkLine2(numbers, rules)
            }
        }
    }

    return sum
}

fun checkLine(numbers: List<Int>, rules: MutableMap<Int, ArrayList<Int>>): Int {
    for (i in 1..<numbers.size) {
        val number = numbers[i]
        rules[number]?.let { rule ->
            numbers.subList(0, i).forEach { num ->
                if (rule.contains(num)) {
                    return 0
                }
            }
        } ?: continue
    }
    return numbers[numbers.size / 2]
}

fun checkLine2(numbers: List<Int>, rules: MutableMap<Int, ArrayList<Int>>): Int {
    // If the list is allready in order we can skip the check
    val preCheck = checkLine(numbers, rules)
    if (preCheck != 0) {
        return 0
    }
    val numbers2 = numbers.sortedWith(CustomComparator(rules))
    return numbers2[numbers2.size / 2]
}

class CustomComparator(private val rules: MutableMap<Int, ArrayList<Int>>) : Comparator<Int> {
    override fun compare(o1: Int, o2: Int): Int {
        val o1Rules = rules[o1] ?: emptyList()
        val o2Rules = rules[o2] ?: emptyList()

        return when {
            o1Rules.contains(o2) -> -1
            o2Rules.contains(o1) -> 1
            else -> o1.compareTo(o2)
        }
    }
}


