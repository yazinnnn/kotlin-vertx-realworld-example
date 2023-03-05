package io.realworld.service

import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.Future
import io.vertx.core.json.JsonObject

@ProxyGen
@VertxGen
interface UserService {
  fun createUser(user: JsonObject): Future<JsonObject>

  fun getCurrentUser(uid: Long): Future<JsonObject>

  fun login(email:String,password:String):Future<JsonObject>

  fun updateUser(uid: Long, user: JsonObject):Future<JsonObject>
}
