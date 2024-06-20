package com.mercadolivro.repository

import com.mercadolivro.entities.BookEntity
import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.BookStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<BookEntity, Int> {
    fun findByStatus(status: BookStatus, pageable: Pageable): Page<BookEntity>
    fun findByCustomer(customer: CustomerEntity): List<BookEntity>
}
