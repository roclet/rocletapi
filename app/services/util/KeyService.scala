package services.util

import java.util.UUID

import conf.util.Util
import domain.util.Keys
import repositories.util.KeysRepository

import scala.collection.immutable.Queue
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by hashcode on 2015/06/14.
 */
trait KeyService {
  def createKey(): Future[String]

  def getKey(): Future[Seq[Keys]]
}

object KeyService {
  def apply(): KeyService = new KeyServiceImpl

  private class KeyServiceImpl extends KeyService {
    override def createKey(): Future[String] = {
      val key = getKey() map (keyvalues =>
        if (keyvalues != Nil) {
          keyvalues.head
          KeysRepository.deleteKey(keyvalues.head.id)
          val newKey = Util.md5Hash(UUID.randomUUID().toString)
          KeysRepository.save(Keys(newKey, newKey))
          newKey
        } else {
          val generateKey = Util.md5Hash(UUID.randomUUID().toString)
          KeysRepository.save(Keys(generateKey, generateKey))
          generateKey
        }
        )
      key
    }

    override def getKey(): Future[Seq[Keys]] = {
      KeysRepository.getAllkeys map (keys =>
        keys match {
          case Queue(x, _*) => keys
          case Queue() => Nil
        })
    }
  }

}
