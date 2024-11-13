package br.com.coutomariel.pedidosapi.controller

import br.com.coutomariel.pedidosapi.entity.Order
import br.com.coutomariel.pedidosapi.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Pedidos", description = "")
@RestController
@RequestMapping("api/v1/orders")
class OrderController(
    private val orderService: OrderService
) {

    private val logger = LoggerFactory.getLogger(OrderController::class.java)

    @Operation(
        summary = "create new order", description = "contain operations to create new order",
        responses = [
            ApiResponse(
                responseCode = "201",
                description = "success on create new order",
                content = [Content(mediaType = "application/json", schema = Schema(implementation= Order::class))]

            )]
    )
    @PostMapping
    fun create(@RequestBody order: Order): ResponseEntity<Order> {
        logger.info("Order received: $order")
        val orderReceived = orderService.sendOrder(order)
        return ResponseEntity.status(HttpStatus.CREATED).body(orderReceived)
    }

}