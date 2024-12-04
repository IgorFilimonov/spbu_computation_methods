package hw5

class GaussianQF(val n: Int, val a: Double, val b: Double) {
    val coefs = mutableListOf<Double>()
    var roots = mutableListOf<Double>()

    init {
        val polys = mutableListOf<(Double) -> Double>()
        for (i in 0..n) {
            when (i) {
                0 -> {
                    polys.add({ _: Double -> 1.0 })
                }
                1 -> {
                    polys.add({ x: Double -> x })
                }
                else -> {
                    polys.add({ x: Double ->
                        ((2.0 * i - 1) / i) * polys[i - 1](x) * x - ((i - 1.0) / i) * polys[i - 2](x)
                    })
                }
            }
        }
        roots = Secant(polys[n], -1.0, 1.0).findRoots().toMutableList()
        for (i in 0 until n) {
            val temp = polys[n - 1](roots[i])
            coefs.add(2.0 * (1.0 - roots[i] * roots[i]) / (n * n * temp * temp))
        }

        for (i in 0 until n) {
            roots[i] = ((b - a) / 2) * roots[i] + (b + a) / 2
            coefs[i] *= (b - a) / 2
        }
    }

    fun eval(func: (Double) -> Double): Double {
        var result = 0.0
        for (i in 0 until n) {
            result += coefs[i] * func(roots[i])
        }
        return result
    }
}