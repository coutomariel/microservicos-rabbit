package br.com.coutomariel.orders.processor.service

import br.com.coutomariel.orders.processor.entity.Order
import br.com.coutomariel.orders.processor.entity.OrderItem
import br.com.coutomariel.orders.processor.entity.enums.Status.PROCESSED
import br.com.coutomariel.orders.processor.repository.OrderItemRepository
import br.com.coutomariel.orders.processor.repository.OrderRepository
import br.com.coutomariel.orders.processor.repository.ProductRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class ProcessorService(
    private val orderItemRepository: OrderItemRepository,
    private val productRepository: ProductRepository,
    private val orderRepository: OrderRepository
) {

    private val logger = LoggerFactory.getLogger(ProcessorService::class.java)

    fun process(order: Order) {

        order.copy(status = PROCESSED).let { it ->
            it.items.forEach { order -> productRepository.save(order.product) }
            val items = orderItemRepository.saveAll(it.items)
            this.save(it, items)
            logger.info("Order saved on database: $order")
        }
    }

    private fun save(order: Order, items: List<OrderItem>) {
        order.copy(items = items).also { orderRepository.save(it) }
    }

}
