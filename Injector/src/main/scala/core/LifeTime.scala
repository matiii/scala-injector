package core

/**
 * Created by Mateusz on 2015-10-03.
 */
case class LifeTime()
case class Singleton() extends LifeTime
case class Transistent() extends LifeTime
