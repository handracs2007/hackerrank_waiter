// https://www.hackerrank.com/challenges/waiter/problem

fun generatePrimeNumbers(n: Int): List<Int> {
    val numbersList = (2..n).toMutableList()

    for (i in numbersList.indices) {
        if (numbersList[i] != -1) {
            for (j in (i + numbersList[i]) until numbersList.size step numbersList[i]) {
                numbersList[j] = -1
            }
        }
    }

    return numbersList.filter { it != -1 }.toList()
}

fun waiter(number: Array<Int>, q: Int): Array<Int> {
    // Let's pre-generate the prime numbers. We need at least 1200 prime numbers ready.
    // We are using 9738 as it is known that there are 1200 prime numbers below 9738.
    val primeList = generatePrimeNumbers(9738)
    val aList = mutableListOf<Int>()
    val bList = mutableListOf<Int>()
    val result = mutableListOf<Int>()

    var numbers = number.toList()

    // Start the iteration
    for (i in 0 until q) {
        for (j in numbers.size - 1 downTo 0) {
            val num = numbers[j]

            if (num % primeList[i] == 0) {
                // Divisible by the prime number. Append to B list.
                bList.add(num)
            } else {
                // Not-divisible by the prime number. Append to A list.
                aList.add(num)
            }
        }

        // Add the B list to the result
        result.addAll(bList.reversed())

        // If there is another iteration, reinitialise the numbers from A list.
        // After that, make the A and B lists empty again.
        if (i < q - 1) {
            numbers = aList.toList()
            aList.clear()
            bList.clear()
        }
    }

    // Add the result with the remaining items inside the A list
    result.addAll(aList.reversed())
    return result.toTypedArray()
}

fun main() {
    println(waiter(intArrayOf(3, 4, 7, 6, 5).toTypedArray(), 1).toList())
    println(waiter(intArrayOf(3, 3, 4, 4, 9).toTypedArray(), 2).toList())
}