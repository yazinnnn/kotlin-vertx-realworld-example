package io.realworld.service

import io.realworld.entity.UserEntity
import io.realworld.extensions.coroutine
import io.realworld.extensions.mapTo
import io.realworld.extensions.asFuture
import io.realworld.model.NewUser
import io.realworld.model.User
import io.realworld.model.response.Login200Response
import io.smallrye.mutiny.coroutines.awaitSuspending
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.web.handler.HttpException
import io.vertx.kotlin.core.json.jsonObjectOf
import io.vertx.kotlin.coroutines.dispatcher
import io.vertx.serviceproxy.ServiceBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.hibernate.reactive.mutiny.Mutiny
import org.springframework.security.crypto.bcrypt.BCrypt
import kotlin.coroutines.CoroutineContext

class UserServiceImpl(
  private val emf: Mutiny.SessionFactory, private val jwt: JWTAuth, private val vertx: Vertx
) : UserService, CoroutineScope {
  init {
    ServiceBinder(vertx).setAddress("UserServiceImpl").register(UserService::class.java, this)
  }

  override val coroutineContext: CoroutineContext
    get() = vertx.dispatcher() + SupervisorJob()

  override fun login(email: String, password: String): Future<JsonObject> {
    return emf.withSession(coroutine { session ->
      val user = session.createQuery<UserEntity>("from UserEntity where email=?1")
        .setParameter(1, email)
        .singleResultOrNull.awaitSuspending()
        ?: throw HttpException(401, "email or password is invalid")
      val bool = BCrypt.checkpw(password, user.password)
      if (!bool) throw HttpException(401, "email or password is invalid")
      val claim = jsonObjectOf("username" to user.username, "uid" to user.id)
      val token = jwt.generateToken(claim)
      val response = User(email, token, user.username.toString(), user.bio, user.image)
      JsonObject.mapFrom(Login200Response(response))
    }).asFuture()
  }

  override fun createUser(user: JsonObject): Future<JsonObject> {
    return emf.withSession(coroutine { session ->
      val newUser = user.mapTo<NewUser>()
      val userEntity = UserEntity()
      userEntity.email = newUser.email
      userEntity.password = BCrypt.hashpw(newUser.password, BCrypt.gensalt())
      userEntity.username = newUser.username
      session.persist(userEntity).awaitSuspending()
      session.flush().awaitSuspending()
      val claim = jsonObjectOf(
        "username" to userEntity.username, "uid" to userEntity.id
      )
      val token = jwt.generateToken(claim)
      val response = User(newUser.email, token, newUser.username, userEntity.bio, userEntity.image)
      JsonObject.mapFrom(Login200Response(response))
    }).asFuture()
  }

  override fun getCurrentUser(uid: Long): Future<JsonObject> {
    return emf.withSession { session ->
      session.find(UserEntity::class.java, uid).map { userEntity ->
        val claim = jsonObjectOf(
          "username" to userEntity.username, "uid" to userEntity.id
        )
        val token = jwt.generateToken(claim)

        JsonObject.mapFrom(
          Login200Response(
            User(
              userEntity.email!!, token, userEntity.username!!, userEntity.bio, userEntity.image
            )
          )
        )
      }
    }.asFuture()
  }


}
