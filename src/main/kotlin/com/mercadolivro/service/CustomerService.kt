package com.mercadolivro.service

import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.enum.CustomerStatus
import com.mercadolivro.enum.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
){

    fun buscarTodosClientes(name: String?, pageable: Pageable): Page<CustomerEntity> {
        name?.let {
            return customerRepository.findByNameContaining(name, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun buscarClienteEspecifico(id: Int): CustomerEntity
    {
        return customerRepository.findById(id)
            .orElseThrow{
                NotFoundException(
                    Errors.ML0002.message.format(id),
                    Errors.ML0002.code)
            }
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

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }
}