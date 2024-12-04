package hw5

import kotlin.math.abs
import kotlin.math.pow

class Secant(val function: (Double) -> Double, val a: Double, val b: Double) {
    val eps = 10.0.pow(-12)

    fun separateRoots(): List<Pair<Double, Double>> {
        val segmentsNumber = 100
        val step = (b - a) / segmentsNumber
        val segments = mutableListOf<Pair<Double, Double>>()
        var left = a
        for (i in 0 until segmentsNumber) {
            val right = left + step
            val value1 = function(left)
            val value2 = function(right)
            if (value1 * value2 < 0) {
                segments.add(Pair(left, right))
            }
            left = right
        }

        return segments
    }

    fun findRoots(): List<Double> {
        val roots = mutableListOf<Double>()
        separateRoots().forEach { (left, right) ->
            if (function(left) == 0.0) {
                roots.add(left)
            } else if (function(right) == 0.0) {
                roots.add(right)
            } else {
                var prev = left
                var curr = right
                var next = curr - function(curr) / (function(curr) - function(prev)) * (curr - prev)
                while (abs(curr - prev) >= eps) {
                    prev = curr
                    curr = next
                    next = curr - function(curr) / (function(curr) - function(prev)) * (curr - prev)
                }
                roots.add(curr)
            }
        }
        return roots
    }
}