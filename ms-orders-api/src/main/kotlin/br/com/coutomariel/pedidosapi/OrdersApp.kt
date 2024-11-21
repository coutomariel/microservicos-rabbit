package br.com.coutomariel.pedidosapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OrdersApp

fun main(args: Array<String>) {
	runApplication<OrdersApp>(*args)
}
