package io.realworld.service

import io.vertx.codegen.annotations.ProxyGen
import io.vertx.core.Future
import io.vertx.core.json.JsonObject

@ProxyGen
interface UserService {
  fun createUser(user: JsonObject): Future<JsonObject>

  fun getCurrentUser(uid: Long): Future<JsonObject>
}
