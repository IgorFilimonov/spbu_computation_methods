package hw5

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin

class Task5 {
    val function = { x: Double ->
        sin(x) / x
    }
    val melerFunction = { x: Double ->
        cos(x)
    }
    var a = 0.0
    var b = 2.0
    fun printPrompts() {
        println("QFs of Gauss, Meler")
        println("Variant 1: sin(x) / x")
        println("Default parameters: a = 0, b = 2, N = 2,5,6,8")
        println("For Meler QF: cos(x)")
        println("Commands:")
        println("coefs -> printing (point, coef) for all N = 1..8")
        println("test -> testing on a polynomials of the highest degrees")
        println("eval -> calculate the integral from the task")
        println("segment -> setting the segment")
        println("meler -> for Meler QF")
        println("quit -> finishing the task")
    }

    fun printPointsAndCoefs(n: Int, left: Double, right: Double) {
        val qf = GaussianQF(n, left, right)
        println("N = ${n}, (point -> coef):")
        for (j in 0 until n) {
            println("(${qf.roots[j]} -> ${qf.coefs[j]})")
        }
    }

    fun meler() {
        println("Enter N1,N2,N3:")
        val ns = readln().split(" ").map { it.toInt() }
        if (ns.size != 3) {
            println("Not exactly 3 numbers here")
            return
        }
        ns.forEach { n ->
            val mf = MelerQF(n)
            println("N = ${n}")
            for (i in 0 until n) {
                println("(${mf.points[i]} -> ${mf.coefs[i]})")
            }
            val result = mf.eval(melerFunction)
            val trueValue = 2.403939430634413
            println("Result: ${result}, error: ${abs(result - trueValue)}")
            println()
        }
    }

    fun run() {
        printPrompts()
        while (true) {
            val command = readln()
            when (command) {
                "coefs" -> {
                    for (i in 1..8) {
                        printPointsAndCoefs(i, -1.0, 1.0)
                    }
                }
                "test" -> {
                    println("N = 3, f(x) = 4x^5 + 12x - 7, [0, 2]")
                    var qf = GaussianQF(3, 0.0, 2.0)
                    var func = { x: Double -> 4*x.pow(5) + 12*x - 7 }
                    var result = qf.eval(func)
                    var trueValue = 158.0/3
                    println("Result: ${result}, error: ${abs(trueValue - result)}")
                    println()

                    println("N = 4, f(x) = 2x^7 + 5x^2, [0, 2]")
                    qf = GaussianQF(4, 0.0, 2.0)
                    func = { x: Double -> 2*x.pow(7) + 5*x*x }
                    result = qf.eval(func)
                    trueValue = 232.0/3
                    println("Result: ${result}, error: ${abs(trueValue - result)}")
                    println()

                    println("N = 5, f(x) = x^9 + 4x^4 + 12, [0, 2]")
                    qf = GaussianQF(5, 0.0, 2.0)
                    func = { x: Double -> x.pow(9) + 4*x.pow(4) + 12 }
                    result = qf.eval(func)
                    trueValue = 152.0
                    println("Result: ${result}, error: ${abs(trueValue - result)}")
                    println()
                }
                "eval" -> {
                    val ns = listOf(2, 5, 6, 8)
                    ns.forEach { n ->
                        val qf = GaussianQF(n, a, b)
                        println("N = ${n}, (point -> coef):")
                        for (j in 0 until n) {
                            println("(${qf.roots[j]} -> ${qf.coefs[j]})")
                        }
                        val result = qf.eval(function)
                        val trueValue = 1.6054129768026948
                        println("Result: ${result}, error: ${abs(trueValue - result)}")
                    }
                }
                "segment" -> {
                    println("Enter new segment:")
                    val segment = readln().split(" ").map { it.toDouble() }
                    if (segment.size != 2) {
                        println("Not exactly 2 numbers here")
                    } else if (segment[0] >= segment[1]) {
                        println("a should be less than b")
                    } else {
                        a = segment[0]
                        b = segment[1]
                    }
                }
                "meler" -> meler()
                "quit" -> return
            }
        }
    }
}