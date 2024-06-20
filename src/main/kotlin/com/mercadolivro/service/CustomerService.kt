package com.mercadolivro.service

import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.CustomerStatus
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
){

    fun buscarTodosClientes(name: String?): List<CustomerEntity> {
        name?.let {
            return customerRepository.findByNameContaining(name)
        }
        return customerRepository.findAll().toList()
    }

    fun buscarClienteEspecifico(id: Int): CustomerEntity
    {
        return customerRepository.findById(id).orElseThrow()
    }

    fun cadastrarCliente(customerRequest: CustomerEntity){
        println(customerRequest)
        customerRepository.save(customerRequest)
    }

    fun atualizarCliente(customerRequest: CustomerEntity) {
        if(!customerRepository.existsById(customerRequest.id!!))
            throw Exception()

        customerRepository.save(customerRequest)
    }

    fun apagarCliente(id: Int) {
        val customer = buscarClienteEspecifico(id)

        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}