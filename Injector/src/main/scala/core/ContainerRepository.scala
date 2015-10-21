package core

/**
 * Created by mmazur01 on 20-10-2015.
 */
trait ContainerRepository {

  private var singletons = Map[Class[_], Any]()
  private var tranistents = Map[Class[_], Class[_]]()
  private var lifetimes = Map[Class[_], LifeTime]()

//  protected def getSingleton[A](): Class[_] = {
//
//  }
}
