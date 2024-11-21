package br.com.coutomariel.orders.processor.listener

import br.com.coutomariel.orders.processor.entity.Order
import br.com.coutomariel.orders.processor.service.ProcessorService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
class ProcessorListener(
    private val processorService: ProcessorService
) {

    private val logger = LoggerFactory.getLogger(ProcessorListener::class.java)

    @RabbitListener(queues = ["pedidos.v1.pedido-criado.gerar-processamento"])
    fun processMessage(order: Order) {
        logger.info("Process generated: $order")
        processorService.process(order)
    }

}