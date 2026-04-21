package com.dunklerfalke.routes.index

import io.javalin.http.Context
import com.dunklerfalke.routes.* 

val get: Route= new Route {
  val path = "/"
  val method = Method.GET 
  val handler = (ctx) => {
    ctx.result("Back")
  }
 }

