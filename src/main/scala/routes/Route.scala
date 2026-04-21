package com.dunklerfalke.routes

import com.dunklerfalke.routes.* 

import io.javalin.http.Context
import io.javalin.http.Handler

enum Method:
  case
      GET,
      HEAD,
      OPTIONS,
      TRACE,
      PUT,
      DELETE,
      POST,
      PATCH,
      CONNECT
      


trait Route {
  def path: String
  def handler: Handler
  def method: Method
}


val routes: List[Route] = List(
  index.get
);
 
