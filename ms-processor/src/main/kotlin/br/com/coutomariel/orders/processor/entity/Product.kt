package br.com.coutomariel.orders.processor.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "product")
data class Product(
    @field:Id
    val id: UUID = UUID.randomUUID(),
    @field:Column(nullable = false)
    val name: String,
    @field:Column(nullable = false)
    val price: Double
)