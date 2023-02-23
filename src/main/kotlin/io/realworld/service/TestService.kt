package io.realworld.service

import io.vertx.codegen.annotations.ProxyGen
import io.vertx.codegen.annotations.VertxGen
import io.vertx.core.Future

@ProxyGen
@VertxGen
interface TestService {
  fun test(text: String): Future<String>
}
