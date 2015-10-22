package core

/**
 * Created by Mateusz on 2015-10-03.
 */
class Injector extends Container with ConstructorGetter with ContainerRepository {

  private var singletons = Map[Class[_], Any]()
  private var tranistents = Map[Class[_], Class[_]]()
  private var lifetimes = Map[Class[_], LifeTime]()


  override def getInstance[A](sygnature: Class[A]): A = {
    val lifetime = lifetimes.get(sygnature)
    if (lifetime.isEmpty) throw new NoSuchElementException(s"You need to register service $sygnature or use method buildUp")
    lifetime.get match {
      case Singleton => singletons.get(sygnature).get.asInstanceOf[A]
      case Transistent => instanceCreator[A](tranistents.get(sygnature).get.asInstanceOf[Class[A]])
    }
  }

  private def instanceCreator[A](tocreate: Class[A]): A = {
    val mainCtr = getCtr(tocreate)
    var parameters = List[Any]()
    mainCtr.getParameterTypes.foreach( cls => parameters = parameters.::(getInstance(cls)))
    var instance: Any = Nil
    if (parameters.isEmpty) instance = mainCtr.newInstance() else instance = mainCtr.newInstance(parameters)
    instance.asInstanceOf[A]
  }

  override def register[A, B <: A](sygnature: Class[A], target: Class[B], lifeTime: LifeTime): Unit = lifeTime match {
      case Singleton =>
        lifetimes = lifetimes + (sygnature -> lifeTime)
        singletons = singletons + (sygnature -> instanceCreator[B](target))
      case Transistent =>
        lifetimes = lifetimes + (sygnature -> lifeTime)
        tranistents = tranistents + (sygnature -> target)
  }


  override def registerDecorator[A, B <: A](sygnature: Class[A], target: Class[B], lifeTime: LifeTime = Transistent, order: Int = 0): Unit = {

  }

  override def register[A, B <: A](sygnature: Class[A], inject: () => B, lifeTime: LifeTime): Unit = {

  }

  //self
  override def register[A](target: Class[A], lifeTime: LifeTime = Transistent): Unit = {

  }

  override def buildUp[A](target: Class[A]): A = {
    instanceCreator(target)
  }

  override def validate(): Unit ={

  }

  override def setInterceptor(sygnature: Class[_], interceptor: Interceptor): Unit = {

  }
}
