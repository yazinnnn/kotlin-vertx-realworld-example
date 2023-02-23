package io.realworld.service

import io.vertx.core.Future

class TestServiceImpl : TestService {
  override fun test(text: String): Future<String> {
    return Future.succeededFuture(text.uppercase())
  }
}
