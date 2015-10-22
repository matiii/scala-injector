import test.{Service2Impl, Service2}

val t = classOf[Service2Impl]

val ctr = t.getConstructors()(0)

val para = ctr.getParameterTypes