import core.{Transistent, Injector}
import org.scalatest.{Matchers, FlatSpec}
import test.{Service2Impl, Service2, Service1Impl, Service1}

/**
 * Created by mmazur01 on 22-10-2015.
 */
class InjectorTest extends FlatSpec with Matchers{

  "Injector" should "correct register service without contructor" in {
    val injector = new Injector
    injector.register(classOf[Service1], classOf[Service1Impl], Transistent)
    val instance = injector.getInstance(classOf[Service1])
    instance.getClass should be (classOf[Service1Impl])
  }

  "Injector" should "correct register service with constructor which has service" in {
    val injector = new Injector
    injector.register(classOf[Service2], classOf[Service2Impl], Transistent)
    injector.register(classOf[Service1], classOf[Service1Impl], Transistent)
    val instance = injector.getInstance(classOf[Service2])
    instance.getClass should be (classOf[Service2Impl])
  }

}
