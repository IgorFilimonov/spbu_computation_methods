package hw4

class IntegrationMethods(
    var function: (Double) -> Double,
    val a: Double, val b: Double
) {
    fun leftRectangle() = (b - a) * function(a)
    fun rightRectangle() = (b - a) * function(b)
    fun middleRectangle() = (b - a) * function((a + b) / 2)
    fun trapezoid() = (b - a) * (function(a) + function(b)) / 2
    fun simpson() = (b - a) / 6 * (function(a) + 4 * function((a + b) / 2) + function(b))
    fun threeEight(): Double {
        val h = (b - a) / 3
        return (b - a) * (function(a) / 8 + 3 * function(a + h) / 8
                + 3 * function(a + 2 * h) / 8 + function(b) / 8)
    }
}