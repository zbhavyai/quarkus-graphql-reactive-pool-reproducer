# Quarkus GraphQL Reactive Pool Reproducer

A small repoducer for issue with SQL connections pool with hibernate reactive and panache with GraphQL.

## Steps to reproduce

1. Start the postgresql server, and run the sql script [init.sql](init.sql) to create `quarkustest` database and user with password `sqlquarkustest`. These details are used in the [application.properties](src/main/resources/application.properties) file.

2. Run the application.

   ```
   $ mvn quarkus:dev
   ```

3. \[OPTIONAL\] Open the terminal and send GET request to the REST endpoint. Use it as many times without any issues.

   ```
   $ curl http://localhost:8080/rest/allFilms | json_pp -json_opt pretty,canonical
   ```

4. Open the terminal and try sending the same request to the GraphQL endpoint. Send it 5 times to reproduce the issue.

   ```
   $ curl -g -X POST -H "Content-Type: application/graphql" -d "query getMeAllFilms {
       allFilms {
           director
           episodeID
           id
           releaseDate
           title
       }
   }" http://localhost:8080/graphql | json_pp -json_opt pretty,canonical
   ```

5. After the fifth request is made, `null` is returned (after waiting for some time). At this stage, both REST and GraphQL endpoints don't work anymore. However, the postgresql server is running and accessible from outside the application.

## Exception stacktrace

```
ERROR [org.hib.rea.errors] (vert.x-eventloop-thread-1) HR000057: Failed to execute statement [$1select film0_.id as id1_0_, film0_.director as director2_0_, film0_.episodeid as episodei3_0_, film0_.releasedate as released4_0_, film0_.title as title5_0_ from film film0_]: $2could not execute query: java.util.concurrent.CompletionException: io.vertx.core.impl.NoStackTraceThrowable: Timeout
        at java.base/java.util.concurrent.CompletableFuture.encodeThrowable(CompletableFuture.java:331)
        at java.base/java.util.concurrent.CompletableFuture.completeThrowable(CompletableFuture.java:346)
        at java.base/java.util.concurrent.CompletableFuture$UniApply.tryFire(CompletableFuture.java:632)
        at java.base/java.util.concurrent.CompletableFuture.postComplete(CompletableFuture.java:506)
        at java.base/java.util.concurrent.CompletableFuture.completeExceptionally(CompletableFuture.java:2088)
        at io.vertx.core.Future.lambda$toCompletionStage$2(Future.java:362)
        at io.vertx.core.impl.future.FutureImpl$3.onFailure(FutureImpl.java:153)
        at io.vertx.core.impl.future.FutureBase.emitFailure(FutureBase.java:75)
        at io.vertx.core.impl.future.FutureImpl.tryFail(FutureImpl.java:230)
        at io.vertx.core.impl.future.Mapping.onFailure(Mapping.java:45)
        at io.vertx.core.impl.future.FutureBase.emitFailure(FutureBase.java:75)
        at io.vertx.core.impl.future.FutureImpl.tryFail(FutureImpl.java:230)
        at io.vertx.core.impl.future.PromiseImpl.tryFail(PromiseImpl.java:23)
        at io.vertx.core.impl.future.PromiseImpl.onFailure(PromiseImpl.java:54)
        at io.vertx.core.impl.future.PromiseImpl.handle(PromiseImpl.java:43)
        at io.vertx.core.impl.future.PromiseImpl.handle(PromiseImpl.java:23)
        at io.vertx.sqlclient.impl.pool.SqlConnectionPool$1PoolRequest.lambda$null$1(SqlConnectionPool.java:218)
        at io.vertx.core.net.impl.pool.SimpleConnectionPool$Cancel.run(SimpleConnectionPool.java:674)
        at io.vertx.core.net.impl.pool.CombinerExecutor.submit(CombinerExecutor.java:50)
        at io.vertx.core.net.impl.pool.SimpleConnectionPool.execute(SimpleConnectionPool.java:245)
        at io.vertx.core.net.impl.pool.SimpleConnectionPool.cancel(SimpleConnectionPool.java:636)
        at io.vertx.sqlclient.impl.pool.SqlConnectionPool$1PoolRequest.lambda$onEnqueue$2(SqlConnectionPool.java:215)
        at io.vertx.core.impl.VertxImpl$InternalTimerHandler.handle(VertxImpl.java:893)
        at io.vertx.core.impl.VertxImpl$InternalTimerHandler.handle(VertxImpl.java:860)
        at io.vertx.core.impl.EventLoopContext.emit(EventLoopContext.java:50)
        at io.vertx.core.impl.DuplicatedContext.emit(DuplicatedContext.java:168)
        at io.vertx.core.impl.AbstractContext.emit(AbstractContext.java:53)
        at io.vertx.core.impl.VertxImpl$InternalTimerHandler.run(VertxImpl.java:883)
        at io.netty.util.concurrent.PromiseTask.runTask(PromiseTask.java:98)
        at io.netty.util.concurrent.ScheduledFutureTask.run(ScheduledFutureTask.java:170)
        at io.netty.util.concurrent.AbstractEventExecutor.safeExecute(AbstractEventExecutor.java:164)
        at io.netty.util.concurrent.SingleThreadEventExecutor.runAllTasks(SingleThreadEventExecutor.java:469)
        at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:503)
        at io.netty.util.concurrent.SingleThreadEventExecutor$4.run(SingleThreadEventExecutor.java:986)
        at io.netty.util.internal.ThreadExecutorMap$2.run(ThreadExecutorMap.java:74)
        at io.netty.util.concurrent.FastThreadLocalRunnable.run(FastThreadLocalRunnable.java:30)
        at java.base/java.lang.Thread.run(Thread.java:829)
Caused by: io.vertx.core.impl.NoStackTraceThrowable: Timeout
```
