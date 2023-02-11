package chapter2

fun main(args: Array<String>) {

//2.1 类型声明
    val string: String = "string"
    val int: Int = 1
    val long: Long = 2L
    val float: Float = 2.1F
    val double: Double = 2.2


    fun print1() {
        println(string.javaClass.name)
        println(int.javaClass.name)
        println(long.javaClass.name)
        println(float.javaClass.name)
        println(double.javaClass.name)
    }
    print1()

//2.2 val var
    fun cal(list: List<Int>): Int {
        var res = 0
        for(el in list){
            res *= el
            res += el
        }
        return res
    }

//2.3 高阶函数和lambda

    //在函数中定义局部函数
    fun foo(x: Int) {
        fun double(y: Int): Int {
            return y*2
        }
        println(double(x))
    }

    //lambda
    val sum = {x: Int, y: Int -> x + y }

    val sum1: (Int, Int) -> Int = { x, y -> x + y }

    //返回不是lambda时，默认最后一行的表达式为返回值
    val foo2 = { x: Int ->
        val y = x + 1
        y //返回值是y
    }

    //it 和 invoke
    fun foo3(int: Int) = {
        print(int)
    }
    listOf(1, 2, 3).forEach{ foo3(it)() }//用括号代替.invoke()

    //Kotlin闭包可以对外部变量进行修改
    var sum2=0
    listOf(1,2,3).filter { it>0 }.forEach{
        sum2 += it
    }
    println(sum2)

//2.4 面向表达式编程
    //if表达式
    fun isExpression(flag: Boolean) {
        val a = if (flag) "dive into Kotlin" else ""
        println(a.uppercase())
    }
    isExpression(true)



    //when表达式 从上到下
    fun schedule(sunny: Boolean, day: Day) = when {
        day == Day.SAT -> {}
        day == Day.SUN -> {}
        day == Day.FRI -> {}
        sunny -> {}
        else -> {}
    }

    //for循环和范围表达式
    for (i in 1..10) { print(i) }

    for (i in 1..10 step 2) { print(i) }

    for (i in 1 downTo  10) { print(i) }

    for (i in 1 until 10) { print(i) }

//2.5 字符串
    val str = "hello world!"
    val rawString = """
        \n hello world
        \n hello kotlin
    """
    for (i in str.uppercase()) { print(i) }
    println(rawString)

    fun message(name: String, lang: String) = "Hi" + name + ", welcome to " + lang + "!"
    message("Shaw", "Kotlin")

}

//枚举类
enum class Day{
    MON,
    TUE,
    WEN,
    THU,
    FRI,
    SAT,
    SUN
}

enum class DayOfWeek(val day: Int) {
    MON(1),
    TUE(2),
    WEN(3),
    THU(4),
    FRI(5),
    SAT(6),
    SUN(7)
    ;

    fun getDayNumber(): Int {
        return day
    }
}








