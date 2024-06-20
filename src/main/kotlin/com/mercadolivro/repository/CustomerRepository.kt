package com.mercadolivro.repository

import com.mercadolivro.entities.CustomerEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CustomerRepository : JpaRepository<CustomerEntity, Int> {

    fun findByNameContaining( name: String, pageable: Pageable): Page<CustomerEntity>
}