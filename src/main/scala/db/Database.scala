package com.dunklerfalk.db
import java.sql.*

val db: Connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres?user=postgres&password=postgres")
