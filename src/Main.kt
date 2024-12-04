import hw2.Task2
import hw4.Task4
import hw5.Task5

fun main() {
    println("Select a task to run:")
    val number = readln()
    when (number) {
        "2" -> Task2().run()
        "4.1" -> Task4().run()
        "5.2" -> Task5().run()
    }
}