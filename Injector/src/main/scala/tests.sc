import core.{Transistent, Singleton, Injector}


trait Service1
class ServiceImp extends Service1

trait Service2
class Service2Impl(service: Service1) extends Service2

val injector = new Injector()

injector.register(classOf[Service1], classOf[ServiceImp], Transistent)
injector.register(classOf[Service2], classOf[Service2Impl], Transistent)


injector.getInstance(classOf[Service1])
injector.getInstance(classOf[Service2])

injector.validate()
//classOf[String].getDeclaredMethods.


