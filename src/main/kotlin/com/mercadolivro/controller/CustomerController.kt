package com.mercadolivro.controller

import com.mercadolivro.service.CustomerService
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.extension.toCustomerEntity
import com.mercadolivro.extension.toResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController
    (val customerService: CustomerService){

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosClientes(
        @RequestParam name: String?,
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): Page<CustomerResponse> {
        return customerService.buscarTodosClientes(name, pageable).map { it.toResponse() }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscaClienteEspecifico(
        @PathVariable(required = false) id: Int
    ): CustomerResponse
    {
        return customerService.buscarClienteEspecifico(id).toResponse()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun cadastrarCliente(
        @RequestBody customerRequest:PostCustomerRequest
    ){
        customerService.cadastrarCliente(customerRequest.toCustomerEntity())
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun atualizarCliente(
        @PathVariable id: Int,
        @RequestBody customerRequest: PutCustomerRequest
    )
    {
        val customerSaved = customerService.buscarClienteEspecifico(id)
         customerService.atualizarCliente(customerRequest.toCustomerEntity(customerSaved))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun apagarCliente(
        @PathVariable id: Int
    )
    {
        customerService.apagarCliente(id)
    }
}