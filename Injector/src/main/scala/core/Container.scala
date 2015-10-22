package core

/**
 * Created by Mateusz on 2015-10-03.
 */
trait Container {

  def getInstance[A](sygnature: Class[A]): A
  def register[A, B <: A](sygnature: Class[A], target: Class[B], lifeTime: LifeTime): Unit
  def register[A, B <: A](sygnature: Class[A], inject: () => B, lifeTime: LifeTime): Unit
  def register[A](target: Class[A], lifeTime: LifeTime = Transistent): Unit
  def registerDecorator[A, B <: A](sygnature: Class[A], target: Class[B], lifeTime: LifeTime = Transistent, order: Int = 0): Unit
  def buildUp[A](target: Class[A]): A
  def setInterceptor(sygnature: Class[_], interceptor: Interceptor): Unit
  def validate(): Unit
}
