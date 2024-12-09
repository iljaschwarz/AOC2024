fun main() {
    fun part1(input: List<String>): Any {
        val fileSystem = mutableListOf<Int?>()
        var file = true
        var id = 0
        val fileSystemLine = input[0]
        for (i in fileSystemLine.indices) {
            val x = fileSystemLine[i].toString().toInt()
            if (file) {
                for (i in 0 until x) {
                    fileSystem.add(id)
                }
                id++
                file = false
            } else {
                for (i in 0 until x) {
                    fileSystem.add(null)
                }
                file = true
            }
        }

        move(fileSystem)
        return checkSum(fileSystem)
    }

    fun part2(input: List<String>): Any {
        val fileSystem = mutableListOf<Int?>()
        val fileSystemLine = input[0]
        val location = MutableList(fileSystemLine.length) { 0 }
        val size = MutableList(fileSystemLine.length) { 0 }
        var file = true
        var id = 0

        for (i in fileSystemLine.indices) {
            val x = fileSystemLine[i].toString().toInt()
            if (file) {
                // Record the location and size of the file
                location[id] = fileSystem.size
                size[id] = x
                for (i in 0 until x) {
                    fileSystem.add(id)
                }
                id++
                file = false
            } else {
                for (i in 0 until x) {
                    fileSystem.add(null)
                }
                file = true
            }
        }
        move2(fileSystem, location, size)
        return checkSum(fileSystem)

    }

    val input = readInput("Day09")
    // val input = listOf("2333133121414131402")

    part1(input).println()
    part2(input).println()
}

fun move(fileSystem: MutableList<Int?>) {
    // Find the first free space
    var freeSpace = 0
    while (fileSystem[freeSpace] != null) {
        freeSpace++
    }
    // Find the last file
    var lastPos = fileSystem.size - 1
    while (fileSystem[lastPos] == null) {
        lastPos--
    }
    // Move the files
    while (lastPos > freeSpace) {
        fileSystem[freeSpace] = fileSystem[lastPos]
        fileSystem[lastPos] = null

        // Find the next free space
        while (fileSystem[freeSpace] != null) {
            freeSpace++
        }
        // Find the next file
        while (fileSystem[lastPos] == null) {
            lastPos--
        }
    }
}

fun move2(fileSystem: MutableList<Int?>, locations: List<Int>, sizes: List<Int>) {
    var curFile = 0
    while (sizes[curFile] > 0) {
        curFile += 1
    }
    curFile -= 1

    for (i in curFile downTo 0) {
        var freeSpace = 0
        var firstFree = 0
        while (firstFree < locations[i] && freeSpace < sizes[i]) {
            firstFree += freeSpace
            freeSpace = 0
            // Find the first free space
            while (fileSystem[firstFree] != null) {
                firstFree++
            }
            // Find the size of the free space
            while (firstFree + freeSpace < fileSystem.size && fileSystem[firstFree + freeSpace] == null) {
                freeSpace++
            }
        }
        // If the file is already in the right place
        if (firstFree >= locations[i]) {
            continue
        }
        // Move the file
        for (index in firstFree..<firstFree + sizes[i]) {
            fileSystem[index] = i
        }
        // Clear the old location
        for (index in locations[i]..<locations[i] + sizes[i]) {
            fileSystem[index] = null
        }
    }
}

fun checkSum(fileSystem: MutableList<Int?>): Long {
    var sum = 0L
    for (i in fileSystem.indices) {
        if (fileSystem[i] != null) {
            sum += i * fileSystem[i]!!
        }
    }
    return sum
}
