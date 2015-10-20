package core

/**
 * Created by Mateusz on 2015-10-03.
 */
class Injector extends Container with ConstructorGetter with ContainerRepository {

  private var singletons = Map[Class[_], Any]()
  private var tranistents = Map[Class[_], Class[_]]()
  private var lifetimes = Map[Class[_], LifeTime]()


  def getInstance[A](): A = {
    val _type = classOf[A]
    val lifetime = lifetimes.get(_type)
    if (!lifetime.isEmpty) throw new NoSuchElementException
    lifetime match {
      case Singleton => singletons.get(_type).get.asInstanceOf[A]
      case Transistent => instanceCreator[A](tranistents.get(_type).get.asInstanceOf[Class[A]])
    }
  }

  private def instanceCreator[A](tocreate: Class[A]): A = {
    val mainCtr = getCtr(tocreate)
    var parameters = List[AnyRef]()
    mainCtr.getParameterTypes.foreach( cls => parameters = parameters.::(getInstance[cls.type]()))
    mainCtr.newInstance(parameters).asInstanceOf[A]
  }

  def register[A, B <: A](lifeTime: LifeTime = Transistent): Unit = {
    lifeTime match {
      case Singleton =>
        lifetimes = lifetimes + (classOf[A] -> lifeTime)
        singletons = singletons + (classOf[A] -> instanceCreator[B](classOf[B]))
      case Transistent =>
        lifetimes = lifetimes + (classOf[A] -> lifeTime)
        tranistents = tranistents + (classOf[A] -> classOf[B])
    }
  }


  def registerDecorator[A, B <: A](lifeTime: LifeTime = Transistent, order: Int = 0): Unit = {

  }

  def register[A, B <: A](inject: () => B): Unit = {

  }

  def buidUp[A](): A = {
    instanceCreator(classOf[A])
  }

  def validate(): Unit ={

  }
}
