package repositories.people

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.people.UserOtp
import play.api.cache.redis.Builders.Future
/**
  * Created by kuminga on 2016/08/16.
  */


class UserOtpRepository extends CassandraTable[UserOtpRepository,UserOtp]{
  object orgCode extends StringColumn(this) with PartitionKey[String]
  object email extends StringColumn(this) with PrimaryKey[String]  with ClusteringOrder[String]
  object userOtpId extends StringColumn(this) with PrimaryKey[String] with ClusteringOrder[String] with Descending
  object date extends DateTimeColumn(this) with ClusteringOrder[DateTime] with Descending
  object pin extends StringColumn(this)
  object channel extends StringColumn(this)
  object sessionId extends StringColumn(this)
  object responseTime extends DateTimeColumn(this)
  object status extends StringColumn(this)

  override def fromRow(r:Row):UserOtp={
    UserOtp(
      orgCode(r),
      email(r),
      userOtpId(r),
      channel(r),
      sessionId(r),
      pin(r),
      date(r),
      responseTime(r),
      status(r)
    )
  }
}
object UserOtpRepository extends UserOtpRepository with RootConnector{
  override lazy val tableName = "userotp"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(userotp:UserOtp):Future[ResultSet]={
      insert
          .value(_.orgCode,userotp.orgCode)
          .value(_.email,userotp.email)
          .value(_.userOtpId,userotp.userOtpId)
          .value(_.channel,userotp.channel)
          .value(_.sessionId,userotp.sessionId)
          .value(_.pin,userotp.pin)
          .value(_.date,userotp.date)
          .value(_.responseTime,userotp.responseTime)
          .value(_.status,userotp.status)
          .future()
  }
  def getUserOtpByorgCode(orgCode:String):Future[Seq[UserOtp]]={
      select.where(_.orgCode eqs orgCode).fetchEnumerator() run Iteratee.collect()
  }
  def getUserOtpByemail(orgCode:String,email:String):Future[Seq[UserOtp]]={
      select.where(_.orgCode eqs orgCode).and(_.email eqs email).fetchEnumerator() run Iteratee.collect()
  }
  def getUserOtpByuserOtpId(orgCode:String,email:String,userOtpId:String):Future[Option[UserOtp]]={
     select.where(_.orgCode eqs orgCode).and(_.email eqs email).and(_.userOtpId eqs userOtpId).one()
  }
}