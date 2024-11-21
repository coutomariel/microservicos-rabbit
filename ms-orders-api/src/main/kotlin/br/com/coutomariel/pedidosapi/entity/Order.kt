package br.com.coutomariel.pedidosapi.entity

import br.com.coutomariel.pedidosapi.entity.enums.Status
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.UUID

data class Order(
    val id: UUID = UUID.randomUUID(),
    val customer: String? = null,
    val items: List<OrderItem> = emptyList(),
    val total: Double?,
    val email: String?,
    val status: Status? = Status.PROCESSING,
    @field:JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime = LocalDateTime.now()
)