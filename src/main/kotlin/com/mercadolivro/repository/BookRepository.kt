package com.mercadolivro.repository

import com.mercadolivro.entities.BookEntity
import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.BookStatus
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<BookEntity, Int> {
    fun findByStatus(status: BookStatus): List<BookEntity>
    fun findByCustomer(customer: CustomerEntity): List<BookEntity>
}
