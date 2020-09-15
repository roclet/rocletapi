package services.util

import domain.people.User
import domain.util.Credential
import org.scalatest.{BeforeAndAfterEach, FunSuite}
import services.people.UserService

import scala.concurrent.Await
import scala.concurrent.duration._

/**
  * Created by hashcode on 2016/09/09.
  */
class TokenServiceTest extends FunSuite with BeforeAndAfterEach {

  override def beforeEach() {
    val user = User("john@test.com", "John", "Banda", "JB", "test123", "ACTIVE","MANR-4BJOI")
    UserService.apply.createUser(user)
  }

  test("testCreateNewToken") {

    // Create Login Process and Receive a Token

    val credential = Credential("admin@test.com", "admin")
    val token = Await.result(TokenService.apply().createNewToken(credential), 2.minutes)

    println("The Token is ", token)

  }
////
//  test("testGetTokenOwner") {
//
//    // Create Login Process and Receive a Token
//    val credential = Credential("johnpapa@test.com", "test123")
//    val user = User("john@test.com", "John", "Banda", "JB", "test123", "ACTIVE","MANR-4BJOI")
//
//    val token = Await.result(TokenService.apply().createNewToken(user,credential), 2.minutes)
//    val tokenOwner = TokenService.apply().getEmail(token)
//    println("The Owner is ",tokenOwner)
//    assert(tokenOwner==="john@test.com")
//
//  }
////
//  test("testIsTokenValid") {
//    // Create Login Process and Receive a Token
//    val credential = Credential("john@test.com", "test123")
//    val user = User("john@test.com", "John", "Banda", "JB", "test123", "ACTIVE","MANR-4BJOI")
//
//    val token = Await.result(TokenService.apply().createNewToken(user,credential), 2.minutes)
//    val isTokenValid = Await.result(TokenService.apply().isTokenValid(token,credential.password),2.minutes)
//
//    println("Is this  token valid ", isTokenValid)
//
//    assert(isTokenValid)
//
//  }
////
//  test("testGetTokenRoles") {
//    // Create Login Process and Receive a Token
//    val credential = Credential("john@test.com", "test123")
//    val user = User("john@test.com", "John", "Banda", "JB", "test123", "ACTIVE","MANR-4BJOI")
//
//    val token = Await.result(TokenService.apply().createNewToken(user,credential), 2.minutes)
//    val tokenRoles = TokenService.apply().getTokenRoles(token)
//
//    println("The Token Role  is ", tokenRoles)
//
//  }
////
//  test("testRevokeToken") {
//    // Create Login Process and Receive a Token
//    val credential = Credential("john@test.com", "test123")
//    val user = User("john@test.com", "John", "Banda", "JB", "test123", "ACTIVE","MANR-4BJOI")
//
//    val token = Await.result(TokenService.apply().createNewToken(user,credential), 2.minutes)
//    val tokenRevoked = Await.result(TokenService.apply().revokeToken(token),2.minutes)
//    assert(tokenRevoked.isExhausted)
//
//    val isTokenValid = Await.result(TokenService.apply().isTokenValid(token,credential.password),2.minutes)
//
//    println("Is the Revoked Token Valid  ", isTokenValid)
//
//  }
////
//  test("testSave") {
//    // Create Login Process and Receive a Token
//    val credential = Credential("john@test.com", "test123")
//    val user = User("john@test.com", "John", "Banda", "JB", "test123", "ACTIVE","MANR-4BJOI")
//    val token = Await.result(TokenService.apply().createNewToken(user,credential), 2.minutes)
//    println("The Token is ", token)
//
//  }

}
