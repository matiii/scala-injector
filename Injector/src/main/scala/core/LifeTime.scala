package core

/**
 * Created by Mateusz on 2015-10-03.
 */
trait LifeTime
case object Singleton extends LifeTime
case object Transistent extends LifeTime
case object Scoped extends LifeTime
