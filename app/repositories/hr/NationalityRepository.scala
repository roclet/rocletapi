package repositories.hr

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.hr.{Nationality, Position}

import scala.concurrent.Future

/**
  * Created by hashcode on 2017/04/07.
  * code:String, name:String
  */
class NationalityRepository extends CassandraTable[NationalityRepository, Nationality] {

  object code extends StringColumn(this) with PartitionKey[String]

  object name extends StringColumn(this)

  override def fromRow(r: Row): Nationality = {
    Nationality(
      code(r),
      name(r)
    )
  }
}

object NationalityRepository extends NationalityRepository with RootConnector {
  override lazy val tableName = "nationalities"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session

  def save(nationality: Nationality): Future[ResultSet] = {
    insert
      .value(_.code, nationality.code)
      .value(_.name, nationality.name)
      .future()
  }

  def getNationalities: Future[Seq[Nationality]] = {
    select.fetchEnumerator() run Iteratee.collect()
  }

  def getNationality(code: String): Future[Option[Nationality]] = {
    select.where(_.code eqs code).one()
  }

}