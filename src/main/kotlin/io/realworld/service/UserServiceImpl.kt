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
      val userEntity = session.createQuery<UserEntity>("from UserEntity where email=?1")
        .setParameter(1, email)
        .singleResultOrNull.awaitSuspending()
        ?: throw HttpException(401, "email or password is invalid")
      val bool = BCrypt.checkpw(password, userEntity.password)
      if (!bool) throw HttpException(401, "email or password is invalid")
      genResponse(userEntity)
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
      genResponse(userEntity)
    }).asFuture()
  }

  override fun getCurrentUser(uid: Long): Future<JsonObject> {
    return emf.withSession(coroutine { session ->
      val userEntity = session.find(UserEntity::class.java, uid).awaitSuspending()
      genResponse(userEntity)
    }).asFuture()
  }

  override fun updateUser(uid: Long, user: JsonObject): Future<JsonObject> {
    return emf.withSession(coroutine { session ->
      val userEntity = session.find(UserEntity::class.java, uid).awaitSuspending()
      user.getString("email")?.let { userEntity.email = it }
      user.getString("password")?.let { userEntity.email = it }
      user.getString("username")?.let { userEntity.email = it }
      user.getString("bio")?.let { userEntity.email = it }
      user.getString("image")?.let { userEntity.email = it }
      session.refresh(userEntity).awaitSuspending()
      session.flush().awaitSuspending()
      genResponse(userEntity)
    }).asFuture()

  }

  private fun genResponse(userEntity: UserEntity): JsonObject {
    val claim = jsonObjectOf("username" to userEntity.username, "uid" to userEntity.id)
    val token = jwt.generateToken(claim)
    val user = User(userEntity.email!!, token, userEntity.username!!, userEntity.bio, userEntity.image)
    return JsonObject.mapFrom(Login200Response(user))
  }
}
