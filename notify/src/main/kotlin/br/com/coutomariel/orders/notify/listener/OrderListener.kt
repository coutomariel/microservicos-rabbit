package br.com.coutomariel.orders.notify.listener

import br.com.coutomariel.orders.notify.entity.Order
import br.com.coutomariel.orders.notify.service.EmailService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class OrderListener(
    private val emailService: EmailService
) {
    private val logger = LoggerFactory.getLogger(OrderListener::class.java)

    @RabbitListener(queues = ["pedidos.v1.pedido-criado.gerar-notificacao"])
    fun sendNotification(order: Order) {
        logger.info("Noficication generated: $order")
        emailService.sendEmail(order)
    }
}