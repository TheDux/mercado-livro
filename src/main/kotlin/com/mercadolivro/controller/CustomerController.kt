package com.mercadolivro.controller

import com.mercadolivro.service.CustomerService
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.entities.CustomerEntity
import com.mercadolivro.extension.toCustomerEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/customers")
class CustomerController
    (val customerService: CustomerService){

//    @Autowired
//    val customerService: CustomerService

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun buscarTodosClientes(
        @RequestParam name: String?
    ): List<CustomerEntity> {
        return customerService.buscarTodosClientes(name)
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun buscaClienteEspecifico(
        @PathVariable(required = false) id: Int
    ): CustomerEntity
    {
        return customerService.buscarClienteEspecifico(id)
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