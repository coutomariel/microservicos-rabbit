package br.com.coutomariel.orders.processor.repository

import br.com.coutomariel.orders.processor.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ProductRepository: JpaRepository<Product, UUID>