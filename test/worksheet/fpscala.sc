case class Person(firstname:String, lastname:String)

val people = List(
  Person("Chanda","Phiri"),
  Person("Fred","Joe")
)

val namesStartingwithC = for{
  p <-people // generator
  fname =p.firstname // definition
  if(fname startsWith("C")) // filter


} yield fname.toUpperCase()

abstract class CustomClass[A]{
  def map[B](f:A=>B):CustomClass[B]
  def flatMap[B](f:A=>CustomClass[B]):CustomClass[B]
  def withFilter(p:A=>Boolean):CustomClass[A]
  def foreach(b: A=> Unit):Unit
}

case class Sequence[A](initialElems: A*){
  private val elems = scala.collection.mutable.ArrayBuffer[A]()
  elems ++=initialElems
  def foreach(block: A=>Unit):Unit ={
    elems.foreach(block)
  }
}

val a = Sequence(1,2)
val b =Sequence(1,2,3)
val c= Sequence('a','b','c','d','e')



