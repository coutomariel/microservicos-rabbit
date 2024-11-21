package br.com.coutomariel.orders.processor.entity

import br.com.coutomariel.orders.processor.entity.enums.Status
import br.com.coutomariel.orders.processor.entity.enums.Status.*
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.persistence.EnumType.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "orders")
data class Order(
    @field:Id
    val id: UUID = UUID.randomUUID(),
    val customer: String? = null,
    @field:OneToMany(mappedBy = "order")
    val items: List<OrderItem> = emptyList(),
    val total: Double?,
    val email: String?,
    @field:Enumerated(STRING)
    val status: Status? = PROCESSING,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime
)