package core

/**
 * Created by Mateusz on 2015-10-03.
 */
trait Container {

  def getInstance[A](): A
  def register[A, B <: A](lifeTime: LifeTime = Transistent): Unit
  def registerDecorator[A, B <: A](lifeTime: LifeTime = Transistent, oreder: Int = 0): Unit
  def register[A, B <: A](inject: () => B): Unit
  def buidUp[A](): A
}
