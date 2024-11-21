package br.com.coutomariel.pedidosapi.service

import br.com.coutomariel.pedidosapi.entity.Order
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val rabbitTemplate: RabbitTemplate
) {

    private val logger = LoggerFactory.getLogger(OrderService::class.java)

    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchangeName: String

    fun sendOrder(order: Order): Order {
        rabbitTemplate.convertAndSend(exchangeName, "", order)
        logger.info("Order sended: $order")
        return order
    }

}