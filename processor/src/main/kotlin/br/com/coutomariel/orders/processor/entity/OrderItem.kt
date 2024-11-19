package br.com.coutomariel.orders.processor.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "order_item")
data class OrderItem(
    @field:Id
    val id: UUID = UUID.randomUUID(),
    @field:ManyToOne
    val product: Product,
    val quantity: Int,
    @field:ManyToOne
    val order: Order
)
