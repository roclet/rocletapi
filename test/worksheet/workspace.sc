import java.util.Date

import scalaz._
import Scalaz._

val list = List(1,1,1,1) :: List(2,3,4)


case class OrganisationTarget(
                               orgCode:String,
                               id:String,
                               description:String,
                               targetValue: BigDecimal,
                               date:Date
                             )
object OrganisationTarget {
 implicit val monoid: Monoid[OrganisationTarget] = new Monoid[OrganisationTarget]{
    def append(value1: OrganisationTarget, value2: => OrganisationTarget)=
      OrganisationTarget(
        value1.orgCode,
        value1.id,
        value1.description,
        value1.targetValue+value2.targetValue,
        new Date)

    def zero: OrganisationTarget =  OrganisationTarget(
      "",
      "",
      "",
      BigDecimal("0"),
      new Date())
  }
}


val t1 = OrganisationTarget("1","2","password", BigDecimal(100.00),new Date)
val t2 = OrganisationTarget("1","2","password", BigDecimal(150.00),new Date)
val t3 = OrganisationTarget("1","2","password", BigDecimal(2000.00),new Date)

val targets = Seq(t1,t2,t3).toList

val totals = targets.fold(OrganisationTarget("1","2","password",BigDecimal(0.00),new Date))(_ |+| _)

val list1 = List("1", "2", "3", "x", "z")
val list2 = List("1", "2", "3")

def toInts(maybeInts : List[String]) : List[Int] = {
  maybeInts map (_.toInt)
}

def toInts2(maybeInts : List[String]): ValidationNel[Throwable, List[Int]] = {
  val validationList = maybeInts map { s =>
    Validation.fromTryCatchNonFatal(s.toInt :: Nil).toValidationNel
  }
  validationList reduce (_ +++ _)
}

val res = toInts2(list1) match {
  case Success(v)=> v
  case Failure(err)=> err
}

println(" The Results is ")


