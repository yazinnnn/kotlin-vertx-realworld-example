package io.realworld.api;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class Test {
  private static final Logger log = LoggerFactory.getLogger(Test.class);
  public static Vertx vertx = Vertx.vertx();

  static Future<ArrayList<String>> solution() throws Exception {
    var identity = Future.succeededFuture(new ArrayList<String>());
    var future = IntStream.range(0, 10).boxed()
      .reduce(identity,
        (acc, integer) -> acc.compose(list -> api(integer)
          .map(list::add)
          .map(v -> list)
        )
        , (id, u) -> u);
    log.info("start");
    return future;
  }

  static Future<ArrayList<String>> solution2() throws Exception {
    var future = Future.succeededFuture(new ArrayList<String>());
    var integers = IntStream.range(0, 10).boxed().toList();
    for (Integer integer : integers) {
      future = future.compose(list -> api(integer).map(list::add).map(list));
    }
    return future;
  }


  public static void main(String[] args) throws Exception {
//    var future = solution();
//    future
//      .onComplete(ar -> log.info(ar.result().toString()))
//      .onComplete(v -> vertx.close())
//    ;
//    solution2();
    Thread thread = new Thread(() -> {
      try {
        log.info("thread start");
        vertx.close();
        new CountDownLatch(1).await();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    });
    thread.setDaemon(true);
    thread.start();
  }


  static Future<String> api(Integer integer) {
    return Future.future(promise -> vertx.setTimer(1000, l -> {
      log.info(" api " + integer);
      promise.complete(integer.toString());
    }));
  }
}
