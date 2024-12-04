package hw2

class Newton(val table: List<Pair<Double, Double>>, val x: Double) {
    private fun computeDifferences(): List<Double> {
        val result = mutableListOf(table[0].second)
        var current = mutableListOf<Triple<Double, Double, Double>>()
        table.forEach {
            current.add(Triple(it.first, it.first, it.second))
        }
        for (i in 1 until table.size) {
            val new = mutableListOf<Triple<Double, Double, Double>>()
            for (j in 0 until current.size - 1) {
                new.add(Triple(current[j].first, current[j + 1].second,
                    (current[j + 1].third - current[j].third) / (current[j + 1].second - current[j].first)))
            }
            current = new
            result.add(current[0].third)
        }
        return result
    }
    fun eval(): Double {
        var result = 0.0
        computeDifferences().forEachIndexed { index, coef ->
            var xs = 1.0
            for (i in 0 until index) {
                xs *= x - table[i].first
            }
            result += coef * xs
        }
        return result
    }
}