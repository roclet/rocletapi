package repositories.financials

import com.datastax.driver.core.Row
import com.websudos.phantom.CassandraTable
import com.websudos.phantom.dsl._
import com.websudos.phantom.keys.PartitionKey
import com.websudos.phantom.reactivestreams._
import conf.connection.DataConnection
import domain.financials.admin.MappingType

import scala.concurrent.Future

/**
  * Created by kuminga on 2016/08/26.
  */
class MappingTypeRepository extends CassandraTable[MappingTypeRepository,MappingType]{
     object id extends StringColumn(this) with PartitionKey[String]
     object name extends StringColumn(this)
     object description extends StringColumn(this)
   override def fromRow(r:Row):MappingType={
     MappingType(
       id(r),
       name(r),
       description(r)
     )
  }
}
object MappingTypeRepository extends MappingTypeRepository with RootConnector{
  override lazy val tableName = "mappingtype"

  override implicit def space: KeySpace = DataConnection.keySpace

  override implicit def session: Session = DataConnection.session
  def save(mappingtype:MappingType):Future[ResultSet]={
      insert
            .value(_.id,mappingtype.id)
            .value(_.name,mappingtype.name)
            .value(_.description,mappingtype.description)
            .future()
  }
  def getMappingTypeById(id:String):Future[Option[MappingType]]={
      select.where(_.id eqs id).one()
  }
  def findAll:Future[Seq[MappingType]]={
    select.fetchEnumerator() run Iteratee.collect()
  }
}

