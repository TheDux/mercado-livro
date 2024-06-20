package com.mercadolivro.repository

import com.mercadolivro.entities.CustomerEntity
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<CustomerEntity, Int> {

    fun findByNameContaining( name: String): List<CustomerEntity>
}