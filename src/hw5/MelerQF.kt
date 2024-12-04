package hw5

import kotlin.math.PI
import kotlin.math.cos

class MelerQF(val n: Int) {
    val coefs = mutableListOf<Double>()
    var points = mutableListOf<Double>()
    init {
        for (i in 0 until n) {
            points.add(cos(2.0 * i - 1) / (2.0 * n) * PI)
            coefs.add(PI / n)
        }
    }

    fun eval(func: (Double) -> Double): Double {
        var result = 0.0
        for (i in 0 until n) {
            result += func(points[i])
        }
        result *= coefs[0]
        return result
    }
}