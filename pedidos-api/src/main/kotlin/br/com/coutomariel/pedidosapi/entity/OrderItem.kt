package br.com.coutomariel.pedidosapi.entity

import java.util.UUID

data class OrderItem(
    val id: UUID = UUID.randomUUID(),
    val product: Product,
    val quantity: Int
)
