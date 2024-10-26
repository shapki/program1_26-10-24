import kotlin.math.abs

class Point(var x: Int, var y: Int) {}

class Triangle(val p1: Point, val p2: Point, val p3: Point) {

    fun containsPoint(point: Point): Boolean {
        val isLeft1 = isLeft(p1, p2, point)
        val isLeft2 = isLeft(p2, p3, point)
        val isLeft3 = isLeft(p3, p1, point)

        return (isLeft1 && isLeft2 && isLeft3) || (!isLeft1 && !isLeft2 && !isLeft3)
    }

    // Функция для проверки, лежит ли точка слева от прямой, проходящей через две точки
    private fun isLeft(p1: Point, p2: Point, point: Point): Boolean {
        // Произведение векторов (p1, p2) и (p1, point)
        val crossProduct = (p2.x - p1.x) * (point.y - p1.y) - (point.x - p1.x) * (p2.y - p1.y)
        return crossProduct > 0
    }
}

fun main() {
    println("Введите координаты вершин треугольника (x1 y1 x2 y2 x3 y3):")
    val input = readLine()!!.split(" ")

    if (input.size != 6) {
        println("Некорректный ввод. Должно быть 6 чисел.")
        return
    }

    // Проверка ввода координат на корректность (только цифры и минусы)
    for (i in input.indices) {
        if (!input[i].matches(Regex("^-?\\d+$"))) {
            println("Некорректный ввод. Разрешены только цифры и минусы.")
            return
        }
    }

    val p1 = Point(input[0].toInt(), input[1].toInt())
    val p2 = Point(input[2].toInt(), input[3].toInt())
    val p3 = Point(input[4].toInt(), input[5].toInt())
    val triangle = Triangle(p1, p2, p3)

    println("Введите координаты точки (x y):")
    val pointInput = readLine()!!.split(" ")
    if (pointInput.size != 2) {
        println("Некорректный ввод. Должно быть 2 числа.")
        return
    }

    for (i in pointInput.indices) {
        if (!pointInput[i].matches(Regex("^-?\\d+$"))) {
            println("Некорректный ввод. Разрешены только цифры и минусы.")
            return
        }
    }

    val point = Point(pointInput[0].toInt(), pointInput[1].toInt())

    if (triangle.containsPoint(point)) {
        println("Точка внутри треугольника.")
    } else {
        println("Точка вне треугольника.")
    }
}
