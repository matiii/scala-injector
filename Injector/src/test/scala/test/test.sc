
class Example(val s: String, val i: Int)

val ctor = classOf[Example].getConstructors()(0)
val parameters = new Array[AnyRef](2)
parameters(0) = "Example".asInstanceOf[AnyRef]
parameters(1) = 1.asInstanceOf[AnyRef]

val instance = ctor.newInstance(new Object[]{"Str", 1}).asInstanceOf[Example]

