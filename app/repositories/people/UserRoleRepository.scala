package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserRole
import play.api.cache.redis.Builders.Future
/**
  * Created by kuminga on 2016/08/16.
  */
class UserRoleRepository extends CassandraTable[UserRoleRepository,UserRole]{
  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]
  object roleId extends StringColumn(this)

  override def fromRow(r:Row):UserRole={
    UserRole(
      orgCode(r),
      email(r),
      roleId(r)
    )
  }
}
object UserRoleRepository extends UserRoleRepository with RootConnector{
  override lazy val tableName = "userole"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(userrole:UserRole):Future[ResultSet]={
      insert
        .value(_.orgCode,userrole.orgCode)
         .value(_.email,userrole.email)
         .value(_.roleId,userrole.roleId)
         .future()
  }
  def getUserRoleByorgCode(orgCode:String):Future[Seq[UserRole]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getByUseremail(orgCode:String,email:String):Future[Seq[UserRole]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }


}
