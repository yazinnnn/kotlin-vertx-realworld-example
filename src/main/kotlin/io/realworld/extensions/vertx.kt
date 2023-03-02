package io.realworld.extensions

import io.vertx.core.DeploymentOptions
import io.vertx.core.Future
import io.vertx.core.Launcher
import io.vertx.core.Verticle


inline fun <reified T : Verticle> Verticle.runVerticle(
  block: (DeploymentOptions) -> Unit = {}
): Future<String> = vertx.deployVerticle(T::class.qualifiedName, DeploymentOptions().also(block))

inline fun Verticle.runVerticle(
  noinline supplier: () -> Verticle, block: (DeploymentOptions) -> Unit = {}
): Future<String> = vertx.deployVerticle(supplier, DeploymentOptions().also(block))

inline fun <reified T : Verticle> runVertx(vararg config: String) {
  Launcher.main(arrayOf("run", T::class.qualifiedName, *config))
}
