package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.User

import scala.concurrent.Future

/**
  * Created by hashcode on 2016/10/02.
  */
class UserEmailRepository extends CassandraTable[UserEmailRepository, User] {

  object orgCode extends StringColumn(this) with PrimaryKey[String]

  object email extends StringColumn(this) with PartitionKey[String]

  object password extends StringColumn(this)

  object userStatus extends StringColumn(this)

  object firstname extends StringColumn(this)

  object lastname extends StringColumn(this)

  object middlename extends StringColumn(this)

  override def fromRow(r: Row): User = {
    User(
      email(r),
      firstname(r),
      lastname(r),
      middlename(r),
      password(r),
      userStatus(r),
      orgCode(r)
    )
  }
}

object UserEmailRepository extends UserEmailRepository with RootConnector {

  override lazy val tableName = "useremails"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(user: User): Future[ResultSet] = {
    insert
      .value(_.email, user.email)
      .value(_.firstname, user.firstname)
      .value(_.lastname, user.lastname)
      .value(_.middlename, user.middlename)
      .value(_.password, user.password)
      .value(_.userStatus, user.userStatus)
      .value(_.orgCode, user.orgCode)
      .future()
  }

  def getUser(email: String):  Future[Seq[User]] = {
    select.where(_.email eqs email) .fetchEnumerator() run Iteratee.collect()
  }

}
