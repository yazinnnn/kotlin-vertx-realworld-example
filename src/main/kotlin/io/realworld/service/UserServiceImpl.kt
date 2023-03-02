package io.realworld.service

import io.realworld.entity.UserEntity
import io.realworld.extensions.mapTo
import io.realworld.extensions.toFuture
import io.realworld.model.NewUser
import io.realworld.model.User
import io.realworld.model.response.Login200Response
import io.vertx.core.Future
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.kotlin.core.json.jsonObjectOf
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.security.crypto.bcrypt.BCrypt

class UserServiceImpl(
  private val emf: Mutiny.SessionFactory,
  private val jwt: JWTAuth
) : UserService {
  override fun createUser(user: JsonObject): Future<JsonObject> {
    return emf.withSession { session ->
      val newUser = user.mapTo<NewUser>()
      val userEntity = UserEntity()
      userEntity.email = newUser.email
      userEntity.password = BCrypt.hashpw(newUser.password, BCrypt.gensalt())
      userEntity.username = newUser.username
      session.persist(userEntity)
        .call { -> session.flush() }
        .map {
          val claim = jsonObjectOf(
            "username" to userEntity.username,
            "uid" to userEntity.id
          )
          val token = jwt.generateToken(claim)
          val response = User(newUser.email, token, newUser.username, userEntity.bio, userEntity.image)
          JsonObject.mapFrom(Login200Response(response))
        }
    }.toFuture()
  }

  override fun getCurrentUser(uid: Long): Future<JsonObject> {
    return emf.withSession { session ->
      session.find(UserEntity::class.java, uid)
        .map { JsonObject.mapFrom(it) }
    }.toFuture()
  }
}
