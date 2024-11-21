package br.com.coutomariel.orders.notify.entity

import java.util.UUID

data class OrderItem(
    val id: UUID = UUID.randomUUID(),
    val product: Product,
    val quantity: Int
)
