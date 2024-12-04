package hw4

import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.exp
import kotlin.math.sin

class Task4 {
    val function = { x: Double ->
        exp(x) + sin(x)
    }
    val integral = { x: Double ->
        exp(x) - cos(x)
    }
    var a = 0.0
    var b = 0.0

    fun runAllMethods(func: (Double) -> Double, integr: (Double) -> Double) {
        val J = integr(b) - integr(a)
        val methods = IntegrationMethods(function, a, b)

        println("Left rectangle:")
        var result = methods.leftRectangle()
        println("Result: ${result}, error: ${abs(J - result)}")
        println()

        println("Right rectangle:")
        result = methods.rightRectangle()
        println("Result: ${result}, error: ${abs(J - result)}")
        println()

        println("Middle rectangle:")
        result = methods.middleRectangle()
        println("Result: ${result}, error: ${abs(J - result)}")
        println()

        println("Trapezoid:")
        result = methods.trapezoid()
        println("Result: ${result}, error: ${abs(J - result)}")
        println()

        println("Simpson:")
        result = methods.simpson()
        println("Result: ${result}, error: ${abs(J - result)}")
        println()

        println("3/8:")
        result = methods.threeEight()
        println("Result: ${result}, error: ${abs(J - result)}")
        println()
    }

    fun test1() {
        println("f(x) = 15")
        runAllMethods({ _: Double -> 15.0 }, { x: Double -> 15 * x })

        println("f(x) = 15x")
        runAllMethods({ x: Double -> 15 * x }, { x: Double -> 7.5 * x*x })

        println("f(x) = 15x^2")
        runAllMethods({ x: Double -> 15 * x*x }, { x: Double -> 5 * x*x*x })

        println("f(x) = 15x^3")
        runAllMethods({ x: Double -> 12*x*x*x }, { x: Double -> 3 * x*x*x*x })
    }

    fun test2() {
        println("Left rectangle, d = 0, f(x) = 3")
        val methods = IntegrationMethods({ _: Double -> 3.0 }, a, b)
        var result = methods.leftRectangle()
        println("Result: ${result}, error: ${abs(3*(b - a) - result)}")
        println()

        println("Right rectangle, d = 0, f(x) = 3")
        result = methods.rightRectangle()
        println("Result: ${result}, error: ${abs(3*(b - a) - result)}")
        println()

        println("Middle rectangle, d = 1, f(x) = 2x")
        methods.function = { x: Double -> 2 * x }
        result = methods.middleRectangle()
        println("Result: ${result}, error: ${abs(b*b - a*a - result)}")
        println()

        println("Trapezoid, d = 1, f(x) = 2x")
        result = methods.trapezoid()
        println("Result: ${result}, error: ${abs(b*b - a*a - result)}")
        println()

        println("Simpson, d = 3, f(x) = 4x^3")
        methods.function = { x: Double -> 4 * x*x*x }
        result = methods.simpson()
        println("Result: ${result}, error: ${abs(b*b*b*b - a*a*a*a - result)}")
        println()

        println("3/8, d = 3, f(x) = 4x^3")
        result = methods.threeEight()
        println("Result: ${result}, error: ${abs(b*b*b*b - a*a*a*a - result)}")
        println()
    }

    fun run() {
        println("Approximate calculation of the integral using quadrature formulas")
        println("Enter the integration segment:")
        while (true) {
            val segment = readln().split(" ").map { it.toDouble() }
            if (segment.size != 2) {
                println("Not exactly 2 numbers here")
            } else if (segment[0] >= segment[1]) {
                println("a should be less than b")
            } else {
                a = segment[0]
                b = segment[1]
                break
            }
        }
        runAllMethods(function, integral)

        println("If you want to test on polynomials from zero to third degree inclusive, type 1")
        println("If you want to test on polynomials of degrees corresponding to their algebraic degree of accuracy, type 2")
        println("To quit, type 3")
        while(true) {
            val command = readln().toInt()
            when(command) {
                1 -> test1()
                2 -> test2()
                3 -> return
            }
        }
    }
}