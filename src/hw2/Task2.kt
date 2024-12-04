package hw2

import kotlin.math.abs
import kotlin.math.sin

class Task2 {
    var a: Double = 0.0
    var b: Double = 1.0
    var x = 0.65
    var n = 7
    var m = 15
    val function = { x: Double -> sin(x) - x*x/2 }
    var table = listOf<Pair<Double, Double>>()

    private fun printPrompts() {
        println("Algebraic interpolation problem")
        println("Variant 1: sin(x) - x^2/2")
        println("Default parameters: [0; 1], x = 0.65, degree = 7, number of points = 15, points are evenly spread")
        println("Commands:")
        println("segment -> setting the segment for interpolation")
        println("m -> setting the number of points")
        println("x -> setting the evaluation point")
        println("n -> setting the polynomial degree")
        println("table -> printing the table of function values")
        println("points -> printing the points for interpolation")
        println("eval -> evaluation of the function in x")
        println("quit -> finishing the task")
    }

    private fun createTable() {
        val newTable = mutableListOf<Pair<Double, Double>>()
        for (j in 0..m) {
            val point = a + j*(b - a)/m
            val value = function(point)
            newTable.addLast(Pair(point, value))
        }
        table = newTable
    }

    private fun getPointsForInterpolation(): List<Pair<Double, Double>> {
        var left = table[0].first
        var right = table[n - 1].first
        for (i in 0 until table.size - n) {
            left = table[i].first
            right = table[i + n].first
            if (left + (right - left) / 2 - x > 0) {
                break
            }
        }
        return table.filter { it.first in left..right }
    }

    fun run() {
        printPrompts()
        createTable()
        while (true) {
            val command = readln()
            when (command) {
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
                        createTable()
                    }
                }
                "m" -> {
                    println("Enter new number of points:")
                    val newM = readln().toInt()
                    if (n > newM) {
                        println("n > new m error, try again")
                    } else {
                        m = newM
                    }
                    createTable()
                }
                "x" -> {
                    println("Enter new eval point:")
                    x = readln().toDouble()
                }
                "n" -> {
                    println("Enter new polynomial degree")
                    val newN = readln().toInt()
                    if (newN > m) {
                        println("newN > m error, try again")
                    } else {
                        n = newN
                    }
                }
                "table" -> {
                    println("Current table:")
                    table.forEach { println("(${it.first}, ${it.second})") }
                }
                "points" -> {
                    println("Current points for interpolation:")
                    getPointsForInterpolation().forEach {
                        println("(${it.first}, ${it.second})")
                    }
                }
                "eval" -> {
                    println("Evaluating using Lagrange method:")
                    val lagr = Lagrange(getPointsForInterpolation(), x).eval()
                    println("Result: ${lagr}, error: ${abs(function(x) - lagr)}")
                    println("Evaluating using Newton method:")
                    val newt = Newton(getPointsForInterpolation(), x).eval()
                    println("Result: ${newt}, error: ${abs(function(x) - newt)}")
                }
                "quit" -> return
            }
        }
    }
}