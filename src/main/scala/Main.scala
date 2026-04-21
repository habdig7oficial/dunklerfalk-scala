import io.javalin.Javalin
import java.sql.*
import java.util.concurrent.CountDownLatch

/* Import settings */

import com.dunklerfalk.db.*
import com.dunklerfalke.routes.*

@main def server(): Unit =
  val latch = new CountDownLatch(1)

  val app = Javalin.create(config => {
    for(route <- routes){
      route.method match
        case Method.GET =>     config.routes.get(route.path, route.handler)
        case Method.HEAD =>    config.routes.head(route.path, route.handler)
        case Method.OPTIONS => config.routes.options(route.path, route.handler)
        case Method.PUT =>     config.routes.put(route.path, route.handler)
        case Method.DELETE =>  config.routes.delete(route.path, route.handler)
        case Method.POST =>    config.routes.post(route.path, route.handler)
        case Method.PATCH =>   config.routes.patch(route.path, route.handler)
        // case Method.TRACE =>   config.routes.trace(route.path, route.handler)
        // case Method.CONNECT => config.routes.apiBuilder(() => ApiBuilder.connect(route.path, route.handler))
        case _ => println("Unknow method")
    }
  })


  app.start(7777)
  Runtime.getRuntime().addShutdownHook(new Thread(() => {
    db.close()
    app.stop()
    latch.countDown()
  }))

  latch.await()



/*
config.routes.get("/", ctx => {
    val query = "SELECT VERSION();"
    val pstmt = db.prepareStatement(query)
    //pstmt.setInt(1, 101) // Sets the first '?' to 101


    val rs: ResultSet = pstmt.executeQuery()
  
    while (rs.next()) {
      val name = rs.getString("version")
      println(s"Found User: $name")
    }
    ctx.result("Scala3 > Java")
  }))
 */
