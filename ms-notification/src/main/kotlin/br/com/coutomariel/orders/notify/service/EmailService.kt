package br.com.coutomariel.orders.notify.service

import br.com.coutomariel.orders.notify.entity.Order
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService(
    private val mailSender: JavaMailSender
) {

    fun sendEmail(order: Order) {
        val simpleMailMessage = SimpleMailMessage().apply {
            this.from = "pedidos-api@company.com";
            this.setTo(order.email)
            this.subject = "Order created"
            this.text = buildMessage(order)
        }

        mailSender.send(simpleMailMessage)
    }

    private fun buildMessage(order: Order): String? {
        return "Order ${order.id} created"
    }

}