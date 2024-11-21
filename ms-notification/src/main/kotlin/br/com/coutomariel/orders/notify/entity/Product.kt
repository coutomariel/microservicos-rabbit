package br.com.coutomariel.orders.notify.entity

import java.util.UUID

data class Product(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val price: Double
)