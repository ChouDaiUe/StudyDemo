package chapter5

import java.lang.IndexOutOfBoundsException

fun main(args:Array<String>) {

    val student = Student(Glasses(350.00))
    val s = Seat(student)
    println("该位置上学生的眼镜度数:${s.student?.glasses?.degreeOfMyopia}") //安全调用?.
    val result1 = student.glasses?.degreeOfMyopia?:-1 //Elvis操作符?:
    val result2 = student!!.glasses //非空断言!!.


    val stu1: Any = Student(Glasses(260.00))
    if(stu1 is Student) println(stu1.glasses) //类型智能转换
    //使用if或is来进行智能转换 对可空类型也实用
    val stu2: Student = Student(Glasses(280.00))
    if(stu2.glasses != null) println(stu2.glasses.degreeOfMyopia)


    open class Human
    data class Teacher(val name: String): Human()
    fun getTeacher():Human { //这里return的是父类Human
        return Teacher("jilen")
    }
    val teacher = getTeacher() as Teacher //去掉as Teacher会报错，因为智能转换无法转化父类为子类
    print(teacher.name)

    fun getTeacher2():Human? { //返回可空父类,使用普通的as会抛出异常
        return Teacher("jilen")
    }
    val teacher2 = getTeacher2() as? Teacher //teacher为空时会返回null
    if(teacher2 !== null){
        print(teacher2.name)
    }


    val funList2 = arrayOf(1,2,3,4) //智能转换隐式推断出元素类型
    val funList3 = arrayOf<Int>(1,2,3) //手动指定类型
    val xArray = intArrayOf(1,2,3)

    //泛型 泛型类可以继承另一个类，使用另一个类的方法和属性
    class SmartList<T> : ArrayList<T>(){
        fun find(t:T):T?{
            val index = super.indexOf(t)
            return if (index >= 0) super.get(index) else null
        }
    }
    val smartList = SmartList<String>()
    smartList.add("one")
    println(smartList.find("one"))
    println(smartList.find("two").isNullOrEmpty())//isNullOrEmpty : 为空指针或者字串长度为0时返回true，非空串与可空串均可调用。

    //协变 A是B的子类型，则Generic<A>也是Generic<B>的子类型
    val stringList: List<String> = ArrayList<String>()
    val anyList: List<Any> = stringList //使用时协变，但是协变的T类型无法再作为参数类型

    //逆变 A是B的子类型，则Generic<B>是Generic<A>的子类型
    val numberComparator = Comparator<Number>{ //使用时逆变，但是逆变的T类型无法再作为返回值类型
        n1,n2 -> n1.toDouble().compareTo(n2.toDouble())
    }
    val doubleList = mutableListOf(2.0,3.0)
    doubleList.sortWith(numberComparator)
    val intList = mutableListOf(1,2)
    intList.sortWith(numberComparator)


    //数组copy函数
    //in 版本
    fun <T> copyIn(dest: Array<in T>, src: Array<T>) {
        if (dest.size< src.size) {
            throw IndexOutOfBoundsException()
        } else{
          src.forEachIndexed{index,value -> dest[index] = src[index]}
        }
    }
    //out 版本
    fun <T> copyOut(dest: Array<T>, src: Array<out T>) {
        if (dest.size< src.size) {
            throw IndexOutOfBoundsException()
        } else{
            src.forEachIndexed{index,value -> dest[index] = src[index]}
        }
    }

    var dest = arrayOfNulls<Number>(3)
    val src = arrayOf<Double>(1.0,2.0,3.0)
    copyIn(dest,src)
    copyOut(dest,src)

}


data class Seat(val student: Student?)
data class Student(val glasses: Glasses?)
data class Glasses(val degreeOfMyopia: Double)



