package hw2

class Lagrange(val table: List<Pair<Double, Double>>, val x: Double) {
    fun eval(): Double {
        var result = 0.0
        table.forEach { pair ->
            val x1 = pair.first
            var numerator = pair.second
            var denominator = 1.0
            table.forEach {
                if (x1 != it.first) {
                    val x2 = it.first
                    denominator *= x1 - x2
                    numerator *= x - x2
                }
            }
            numerator /= denominator
            result += numerator
        }
        return result
    }
}