import io.javalin.Javalin;
import java.sql.*
import java.util.concurrent.CountDownLatch;

@main def server(): Unit =
  val latch = new CountDownLatch(1)

  val db: Connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=postgres")

  val app = Javalin.create(config => config.routes.get("/", ctx => {
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

  app.start(7777)
  Runtime.getRuntime().addShutdownHook(new Thread(() => {
    db.close()
    app.stop()
    latch.countDown()
  }))

  latch.await()
