package services

import conf.connection.DataConnection

/**
  * Created by kuminga on 2016/08/18.
  */
trait Service {
  implicit val keyspace = DataConnection.keySpace
  implicit val session = DataConnection.session
}
