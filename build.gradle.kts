import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.8.10"
  kotlin("kapt") version "1.8.10"
  id("org.openapi.generator") version "6.4.0"
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "io.realworld"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.3.8"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "io.realworld.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  kapt("io.vertx:vertx-codegen:$vertxVersion:processor")
  compileOnly("io.vertx:vertx-codegen:$vertxVersion")

  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-web-validation")
  implementation("io.vertx:vertx-auth-jwt")
  implementation("io.vertx:vertx-service-proxy")
  implementation("io.vertx:vertx-web")
  implementation("io.vertx:vertx-service-factory")
  implementation("io.vertx:vertx-web-openapi")
  implementation("io.vertx:vertx-pg-client")
  implementation("io.vertx:vertx-service-discovery")
  implementation("io.vertx:vertx-lang-kotlin-coroutines")
  implementation("io.vertx:vertx-web-api-contract")
  implementation("io.vertx:vertx-lang-kotlin")
  implementation(kotlin("stdlib-jdk8"))
  implementation("com.ongres.scram:client:2.1")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions.jvmTarget = "17"

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf(
    "run",
    mainVerticleName,
    "--redeploy=$watchForChange",
    "--launcher-class=$launcherClassName",
    "--on-redeploy=$doOnChange"
  )
}

openApiGenerate {
  generatorName.set("kotlin-vertx")
  inputSpec.set("$rootDir/openapi.yml")
  outputDir.set("$rootDir/src/main/kotlin")
  configOptions.set(
    mapOf(
      "useVertx" to "true",
      "packageName" to "io.realworld",
      "serializationLibrary" to "jackson",
    )
  )
}
